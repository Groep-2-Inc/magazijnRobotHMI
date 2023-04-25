#include <Arduino.h>
#include <comms.h>
#include <motorController.h>

const int stopButtton = 10;
bool emergency = false;

// Zet de pinmode voor noodstop
void emergyStopSetup(){
	pinMode(stopButtton, INPUT_PULLUP);
}

// Stopt de robot en stuurt melding naar HMI
void stop(){
    // stopt de robot
    stopMovement();
	toSlaveArduino(0);
	emergency = true;
    // Stuurt melding naar de HMI
    toJava(500);
}

unsigned long lastStopPressed = 0;
bool checkEmergencyStop(){
	bool stopPressed = !digitalRead(stopButtton); 
	if(stopPressed){
		if(millis() - lastStopPressed > 250){
			stopPressed = millis();
            lastStopPressed = stopPressed;
			return true;
		}
	}
	return false;
}

// Controleert of de stopknop is ingedrukt
// stopButton -> pin waar de noodstop op is aangesloten
void checkStop(){
	if(checkEmergencyStop()){
		Serial.println("STOP!");
		stop();
	}
}

int isEmergency(){
	return emergency;
}