#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>

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
    // checkStop();

	manualControl();

    fromJava();
}