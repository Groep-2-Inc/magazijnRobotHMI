// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>
#include <endStop.h>
#include <currentPositionController.h>

bool hasHomed = false;
bool moved = false;
int curdata = 0;
int x = 0;
int y = 0;
bool pickingProduct = false;
bool manual = false;
bool sendManualMessage = false;
bool sendHomeMessage = false;

// Sets correct pinmodes
void setup() {  
	emergyStopSetup();
	joystickSetup();
	motorSetup();
	commsSetup();
	encoderSetup();
	endStopSetup();
	// positionSetup();
	}

// Herhaald de volgende code meerder keren
void loop() {
	//check de noodstop
    checkStop();	
	
	//als er nog geen conectie is check voor een conectie (Door Jason Joshua)
	if(!getConection()){
		fromJava();
	}

	//als er geen emergency is beweeg, als er wel een is zet de breakpins aan (Door Jason Joshua)
	if(!isEmergency()){
		//als de robot niet op de manuele stand staat, beweeg dan automatisch (Door Jason Joshua)
		if(!manual){
			//als er een connectie is, ga verder
			if(getConection()){
				//als de robot nog niet home is, ga dan naar home, als die wel home is, beweeg dan automatisch (Door Jason Joshua)
				if(!hasHomed){
					hasHomed = moveToHome();
				}else if(hasHomed){

					if(!sendHomeMessage){
						sendHomeMessage = false;
						toJava(201);
					}

					//als de curdata niet gelijk is aan de data die gekregen is van java, stel deze dan gelijk en zet de x en y op de coordinaten die bij deze data horen (Door Jason Joshua)
					if(curdata != fromJava()){
						curdata = fromJava();
						x = getCorX(curdata);
						y = getCorY(curdata);
					}
					//als x en y niet 0 is en de robot is geen product aan het pakken, beweeg dan naar de coordinaten, anders pak het product op  (Door Jason Joshua)
					if(x != 0 && y != 0 && !pickingProduct){
						//zodra moved false is en move xy true is dan is de robot op de plek waar die moet zijn, zorg dan dat de robot stopt en begint met het pakken van een product (Door Jason Joshua)
						if(moved == false && moveXY(x, y) == true){
							moved = true;
						} else if (moved == true) {
							stopMovement();
							toSlaveArduino(0);
							pickingProduct = true;
						}
					} else if (pickingProduct){
						pickUpProduct();
					}
				}
			}
		} else {
			if(!sendManualMessage){
				sendManualMessage = true;
				toJava(310);
			}
			//als de robot op de manuele stand sta roep de manual control functie aan, delay voor het gelijk laten lopen van de arduinos (Door Jason Joshua)
			manualControl();
			delay(20);
		}
	}else {
		//stuurt melding naar slave Arduino om noodstoplampje te laten branden (Sarah)
		toSlaveArduino(21);
		//zet de breakpin aan (Door Jason Joshua)
		toSlaveArduino(0);
	} 
}
