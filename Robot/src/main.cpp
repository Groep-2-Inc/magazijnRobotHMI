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
	
	// x = getCorX(424);
	// y = getCorY(424);

    checkStop();
	// // checkEndStopX();
	// checkEndStopY();
	
	
	// Serial.println(readX());
	

	if(!isEmergency() ){//&& getConection()){
		if(!hasHomed){
			resetHasMoved();
			toSlaveArduino(11);
			moveToHome();
			hasHomed = true;
		}

		
		// x = getCorX(fromJava());
		// y = getCorY(fromJava());

		// if(x != 0 && y != 0){
		// 	if(moved == false && moveXY(x, y) == true){
		// 	moved = true;
		// 	}
		// }
		
 		
		manualControl();
		
	}else {//if (getConection()){
		//stuurt melding naar slave Arduino om noodstoplampje te laten branden (Sarah)
		toSlaveArduino(21);
		toSlaveArduino(0);
	} 

	// Serial.println(getFromSlave());
	
	// Serial.println(readJoystick());
	
	delay(10);
	
	// readXposition();
	// readEndStop();

    fromJava();
}