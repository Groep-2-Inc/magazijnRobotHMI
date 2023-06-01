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
bool sendRustMessage = false;
bool sendHomeMovementMessage = false;
bool sendFinishMessage = false;
bool sendProductOphalenMessage = false;
bool sendProductOphalenMovingMessage = false;
unsigned long lastSendProductOphalenMessage = 0;

// Sets correct pinmodes
void setup() {  
	emergyStopSetup();
	joystickSetup();
	motorSetup();
	commsSetup();
	encoderSetup();
	endStopSetup();
}

// Herhaald de volgende code meerder keren
void loop() {
	// Als de slave het product heeft verzameld
	if(getFromSlave() == 13){
		// Stuur één keer code 301 naar Java
		while(!sendFinishMessage){
			toJava(301);
			
			delay(500);
			toSlaveArduino(15);
			curdata = fromJava();
			x = getCorX(curdata);
			y = getCorY(curdata);
			resetBoolXY();
			
			pickingProduct = false;
			moved = false;
			// sendProductOphalenMessage = false;
			// sendProductOphalenMovingMessage = false;
			// lastSendProductOphalenMessage = 0;
			
			sendFinishMessage = true;
		}
	}

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
			// Als er een connectie is, ga verder na 2000ms
			// getCommsStartTime() returnt de waarde van wanneer er voor het eerst verbinding is gemaakt
			// toegevoegd zodat Java genoeg tijd heeft om data op te halen vanuit robot 
			// Door Martijn
			if(getConection() && millis() - getCommsStartTime() > 2000){
				//als de robot nog niet home is, ga dan naar home, als die wel home is, beweeg dan automatisch (Door Jason Joshua)
				if(!hasHomed){
					// Versuurd één keer dat hij aan het homen is
					if(!sendHomeMovementMessage){
						toJava(302);
						sendHomeMovementMessage = true;
					}

					hasHomed = moveToHome();
				}else if(hasHomed){
					// Als hij thuis is stuur dan één keer 201 naar Java
					if(!sendRustMessage){
						toJava(201);
						sendRustMessage = true;
					}

					//als de curdata niet gelijk is aan de data die gekregen is van java, stel deze dan gelijk en zet de x en y op de coordinaten die bij deze data horen (Door Jason Joshua)
					if(curdata != fromJava()){
						curdata = fromJava();
						x = getCorX(curdata);
						y = getCorY(curdata);
					}
					//als x en y niet 0 is en de robot is geen product aan het pakken, beweeg dan naar de coordinaten, anders pak het product op  (Door Jason Joshua)
					if(x != 0 && y != 0 && !pickingProduct){
						// Verstuur één keer dat hij een product ophaalt
						if(!sendProductOphalenMessage){
							toJava(300);
							sendProductOphalenMessage = true;
							lastSendProductOphalenMessage = millis();
						}

						// Verstuur één keer dat hij ook tegelijk in beweging is, doe dit alleen wel pas na 1.5sec van vorige bericht zodat Serial genoeg tijd heeft om hem te lezen
						if(!sendProductOphalenMovingMessage && millis() - lastSendProductOphalenMessage > 2000){
							toJava(302);
							sendProductOphalenMovingMessage = true;
						}

						//zodra moved false is en move xy true is dan is de robot op de plek waar die moet zijn, zorg dan dat de robot stopt en begint met het pakken van een product (Door Jason Joshua)
						if(moved == false && moveXY(x, y) == true){
							moved = true;
						} else if (moved == true) {
							stopMovement();
							toSlaveArduino(0);
							pickingProduct = true;
						}
					} else if (pickingProduct){
						if(x != 6){
							sendFinishMessage = false;
							pickUpProduct();
						}
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
