// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <comms.h>
#include <motorController.h>

// stopButton - Definieert de pin van de noodstop.
// emergency - Slaat op of er een noodgeval is.
const int stopButtton = 10;
const int goButton = 7;
bool emergency = false;
bool sendComms = false;

// Zet de pinmode voor noodstop
void emergyStopSetup(){
	pinMode(stopButtton, INPUT_PULLUP);
	pinMode(goButton, INPUT_PULLUP);
}

// Stopt de robot en stuurt melding naar HMI
void emgStop(){
    // stopt de robot
    stopMovement();
	emergency = true;

    // Stuurt melding naar de HMI
	if(!sendComms){
		sendComms = true;
		toJava(500);
	}
}

// Kijkt of de noodstop is ingedrukt en geeft deze lezing een debounce mee.
unsigned long lastStopPressed = 0;
bool checkEmergencyStop(){
	// Leest de waarde van de noodstop uit
	bool stopPressed = !digitalRead(stopButtton); 
	if(stopPressed){
		// Controleert voor debouch
		if(millis() - lastStopPressed > 250){
			lastStopPressed = millis();
			return true;
		}
	}
	return false;
}

// Kijkt of de go-knop is ingedrukt en geeft deze lezing een debounce mee (Sarah)
unsigned long lastGoPressed = 0;
bool checkGoButton(){
	// Leest de waarde van de go-knop uit
	bool goPressed = !digitalRead(goButton); 
	if(goPressed){
		if(millis() - lastStopPressed > 250){
            lastGoPressed = millis();
			return true;
		}
	}
	return false;
}

// Controleert of de stopknop is ingedrukt
// stopButton -> pin waar de noodstop op is aangesloten
void checkStop(){
	if(checkEmergencyStop()){
		emgStop();
		toSlaveArduino(50);
	} else if(checkGoButton()){
		emergency = false;
		toSlaveArduino(51);
	}
}

// Geeft de status van het noodgeval terug
int isEmergency(){
	return emergency;
}