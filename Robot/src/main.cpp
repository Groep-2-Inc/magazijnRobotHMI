// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>
#include <positionController.h>
#include <endStop.h>
#include <currentPositionController.h>

bool hasHomed = false;
bool moved = false;
int x = 0;
int y = 0;

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
	if(!hasHomed){
		resetHasMoved();
		toSlaveArduino(11);
		moveToHome();
		hasHomed = true;
	}


	x = getCorX(fromJava());
	y = getCorY(fromJava());
	

	// x = getCorX(424);
	// y = getCorY(424);

    checkStop();
	// // checkEndStopX();
	// checkEndStopY();
	
	
	// Serial.println(readX());
	

	if(!isEmergency()){
		manualControl();

		if(x != 0 && y != 0){
			if(moved == false && moveXY(x, y) == true){
			moved = true;
			}
		}
		
		// }else if (moved == true){
		// 	x = 7;
		// 	y = 1;
		// 	// moveXY(x, y);
		// 	toSlaveArduino(11);
		// 	resetHasMoved();
		// 	resetBoolXY();
		// 	moved = false;
		// }
	}

	Serial.println(getFromSlave());
	
	
	
	
	
	// readXposition();
	// readEndStop();

    fromJava();
}