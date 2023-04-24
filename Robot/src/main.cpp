#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>
#include <positionController.h>

// Sets correct pinmodes
void setup() {  
	emergyStopSetup();
	joystickSetup();
	motorSetup();
	commsSetup();
	positionSetup();
	pinMode(6, INPUT_PULLUP);
}

// Herhaald de volgende code meerder keren
void loop() {
    // checkStop();

	manualControl();
	// readXposition();

    fromJava();
}