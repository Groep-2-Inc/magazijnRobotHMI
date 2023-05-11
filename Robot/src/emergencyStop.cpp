// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <comms.h>
#include <motorController.h>

// stopButton - Definieert de pin van de noodstop.
// goButton - Definieert de pin die gebruikt wordt om de noodstop op te heffen (Sarah)
// emergency - Slaat op of er een noodgeval is.
const int stopButton = 10;
const int goButton = 7;
bool emergency = false;

// Zet de pinmode voor noodstop
void emergyStopSetup(){
	pinMode(stopButton, INPUT_PULLUP);
	pinMode(goButton, INPUT_PULLUP);
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

// Kijkt of de noodstop is ingedrukt en geeft deze lezing een debounce mee.
unsigned long lastStopPressed = 0;
bool checkEmergencyStop(){
	// Leest de waarde van de noodstop uit
	bool stopPressed = !digitalRead(stopButtton); 
	// Serial.println(stopPressed);
	if(stopPressed){
		if(millis() - lastStopPressed > 250){
			stopPressed = millis();
            lastStopPressed = stopPressed;
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
			goPressed = millis();
            lastGoPressed = goPressed;
			return true;
		}
	}
	return false;
}


// Controleert of de stopknop of is ingedrukt
// Controleert of de go-knop is ingedrukt en heft dan de noodstop op (Sarah)
void checkStop(){
	if(checkEmergencyStop()){
		Serial.println("STOP!");
		stop();
	} else if(checkGoButton()){
		Serial.println("go");
		emergency = false;
	}
}

// Geeft de status van het noodgeval terug
int isEmergency(){
	return emergency;
}