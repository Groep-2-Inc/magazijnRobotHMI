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
bool manual = true;

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
	
	// x = getCorX(424);
	// y = getCorY(424);

    checkStop();
	// // checkEndStopX();
	// checkEndStopY();
	
	
	// Serial.println(readX());

	if(!getConection()){
		fromJava();
	}

	if(!isEmergency()){
		if(!manual){
			if(getConection()){
				if(!hasHomed){
					resetHasMoved();
					toSlaveArduino(11);
					moveToHome();
					hasHomed = true;
				}

				if(curdata != getData()){
					curdata = getData();
					x = getCorX(curdata);
					y = getCorY(curdata);
				}
				

				if(x != 0 && y != 0 && !pickingProduct){
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
		} else {
			manualControl();
			delay(20);
		}
	}else {
		//stuurt melding naar slave Arduino om noodstoplampje te laten branden (Sarah)
		toSlaveArduino(21);
		toSlaveArduino(0);
	} 

	// manualControl();

	// Serial.println(getFromSlave());
	
	// Serial.println(readJoystick());
	
	
	
	// readXposition();
	// readEndStop();

    // fromJava();
}