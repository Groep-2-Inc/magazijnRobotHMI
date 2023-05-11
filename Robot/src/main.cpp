// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>
#include <endStop.h>

// Sets correct pinmodes
void setup() {  
	emergyStopSetup();
	joystickSetup();
	motorSetup();
	commsSetup();
	pinMode(6, INPUT_PULLUP);
}

// Herhaald de volgende code meerder keren
void loop() {
    checkStop();
	if(!isEmergency()){
		// if(getFromSlave() == 24){
			// toSlaveArduino(23);
		// } else if(getFromSlave() == 25){
			manualControl();
		// }
	} else{
		//stuurt melding naar slave Arduino om noodstoplampje te laten branden (Sarah)
		toSlaveArduino(21);
	}

	// readXposition();
	readEndStop();

    fromJava();
}