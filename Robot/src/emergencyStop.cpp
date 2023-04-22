#include <Arduino.h>
#include <comms.h>
#include <motorController.h>

const int stopButtton = 7;

// Zet de pinmode voor noodstop
void emergyStopSetup(){
	pinMode(stopButtton, INPUT);
}

// Leest de stopknop uit
// stopButton -> pin waar de noodstop op is aangesloten
unsigned long laatstIngedrukt = 0;
bool checkStopButton(){
	bool ingedrukt = digitalRead(stopButtton); 
	if(ingedrukt){
		if(millis() - laatstIngedrukt > 250){
			laatstIngedrukt = millis();
			return true;
		}
	}
	return false;
}

// Stopt de robot en stuurt melding naar HMI
void stop(){
    // stopt de robot
    stopMovement();
    // Stuurt melding naar de HMI
    toJava(500);
}

// Controleert of de stopknop is ingedrukt
// stopButton -> pin waar de noodstop op is aangesloten
void checkStop(){
	if(checkStopButton()){
		stop();
	}
}
