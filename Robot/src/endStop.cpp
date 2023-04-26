// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <motorController.h>

// endStopPinx - Definieert de pinmode voor de x-as
// endStopPinY - Definieert de pinmode voor de y-as
// endStopValueX - Slaat de waarde van de endStop op de x-as op
// endStopValueY - Slaat de waarde van de endStop op de y-as op
// atHome - Slaat op of de robot zich in de beginsituatue (home) bevindt
const int endStopPinX = 2;
const int endStopPinY = 4;
int endStopValueX = 0;
int endStopValueY = 0;
bool atHome = false;

// Zet de pinmode voor de eindstop op de x- en y-as
void endStopSetup(){
    pinMode(endStopPinX, OUTPUT);
    pinMode(endStopPinY, OUTPUT);
}

// Kijkt of de eindstop op de x-as is ingedrukt en geeft deze lezing een debounce mee
unsigned long previousPressedX = 0;
bool checkEndStopX(){
	bool endStopXPressed = digitalRead(endStopPinX); 
	if(endStopXPressed){
		if(millis() - previousPressedX > 250){
			previousPressedX = millis();
            endStopValueX = endStopXPressed;
			return true;
		}
	}
	return false;
}

// // Kijkt of de eindstop op de y-as is ingedrukt en geeft deze lezing een debounce mee
unsigned long previousPressedY = 0;
bool checkEndStopY(){
	bool endStopYPressed = digitalRead(endStopPinY); 
	if(endStopYPressed){
		if(millis() - previousPressedY > 250){
			previousPressedY = millis();
            endStopValueY = endStopYPressed;
			return true;
		}
	}
	return false;
}

// Onderstaande code werkt nog niet optimaal, maar zou ervoor moeten zorgen dat er wordt uitgelezen of de robot zich op home bevindt.
// Als dit true is worden de encoders gereset, nu is dit nog een bericht. Ook moet er nog gefixt worden dat dit bericht maar een keer geprint wordt.
void resetEndStop(){
	atHome = false;
}

void readEndStop(){
    if (checkEndStopX() && checkEndStopY() && !atHome){
        atHome = true;
        stopMovement();
        Serial.println("De encoders zijn gereset");
    } else if (!checkEndStopX() || !checkEndStopY()){
		atHome = false;
	}
}