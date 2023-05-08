// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>
#include <positionController.h>
#include <endStop.h>

// Sets correct pinmodes
void setup() {  
	emergyStopSetup();
	joystickSetup();
	motorSetup();
	commsSetup();
	positionSetup();
}

// Herhaald de volgende code meerder keren
void loop() {
    checkStop();

	if(!isEmergency()){
		manualControl();
	}

	// readXposition();
	// readEndStop();

    fromJava();
}