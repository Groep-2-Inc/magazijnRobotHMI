// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <statusLights.h>

const int autoBtn = 4;
const int manualBtn = 10;
bool autoMode = false;

void manualOrAutoButtonsSetup(){
	pinMode(autoBtn, INPUT_PULLUP);
	pinMode(manualBtn, INPUT_PULLUP);
}

// Kijkt of de autoBtn is ingedrukt en geeft deze lezing een debounce mee.
unsigned long lastAutoPressed = 0;
bool checkAutoMode(){
	// Leest de waarde van de autoBtn uit
	bool autoPressed = !digitalRead(autoBtn); 
	if(autoPressed){
		if(millis() - lastAutoPressed > 250){
			autoPressed = millis();
            lastAutoPressed = autoPressed;
			return true;
		}
	}
	return false;
}

// Kijkt of de manualBtn is ingedrukt en geeft deze lezing een debounce mee.
unsigned long lastManualPressed = 0;
bool checkManualMode(){
	// Leest de waarde van de manualBtn uit
	bool manualPressed = !digitalRead(manualBtn); 
	if(manualPressed){
		if(millis() - lastManualPressed > 250){
			manualPressed = millis();
            lastManualPressed = manualPressed;
			return true;
		}
	}
	return false;
}

int checkBtns(){
	if(checkAutoMode()){
		autoMode = true;
		Serial.println("auto");
		// autoLEDOn();
		return 23;
	} else if(checkManualMode()){
		autoMode = false;
		Serial.println("manual");
		// manualLEDOn();
		return 22;
	}
	return 0;
}

bool isAutoMode(){
	Serial.println(autoMode);
	return autoMode;
}
