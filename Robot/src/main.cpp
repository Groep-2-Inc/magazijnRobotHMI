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
  Serial.begin(9600);
}

// Herhaald de volgende code meerder keren
void loop() {
    checkStop();

	manualControl();

    fromJava();
}