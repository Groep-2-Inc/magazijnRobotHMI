// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <motorController.h>

// endStopPinx - Definieert de pinmode voor de x-as
// endStopPinY - Definieert de pinmode voor de y-as
// endStopValueX - Slaat de waarde van de endStop op de x-as op
// endStopValueY - Slaat de waarde van de endStop op de y-as op
// atHome - Slaat op of de robot zich in de beginsituatue (home) bevindt
const int endStopPinX = 5;
const int endStopPinY = 4;
int endStopValueX = 0;
int endStopValueY = 0;
bool atHome = false;

// Zet de pinmode voor de eindstop op de x- en y-as
void endStopSetup(){
    pinMode(endStopPinX, INPUT_PULLUP);
    pinMode(endStopPinY, INPUT_PULLUP);
}

// Kijkt of de eindstop op de x-as is ingedrukt en geeft deze lezing een debounce mee
// aangepast door jason joshua, werkt nu optimaal.
unsigned long previousPressedX = 0;
bool boolSendX = false;
bool checkEndStopX(){
	bool endStopXPressed = digitalRead(endStopPinX); 
	if(endStopXPressed){
		if(millis() - previousPressedX > 250){
			previousPressedX = millis();
            endStopValueX = endStopXPressed;
			boolSendX = true;
		}
	}
	else if(millis() - previousPressedX > 300){
		boolSendX = false;
	}
	return boolSendX;
}

// // Kijkt of de eindstop op de y-as is ingedrukt en geeft deze lezing een debounce mee
// aangepast door jason joshua, werkt nu optimaal.
unsigned long previousPressedY = 0;
bool boolSendY = false;
bool checkEndStopY(){
	bool endStopYPressed = digitalRead(endStopPinY); 
	if(endStopYPressed){
		if(millis() - previousPressedY > 250){
			previousPressedY = millis();
            endStopValueY = endStopYPressed;
			boolSendY = true;
		}
	}
	else if(millis() - previousPressedY > 300){
		boolSendY = false;
	}
	return boolSendY;
}

//update de athome bool (door Jason Joshua)
void updateAtHome(){
	if(boolSendX == true && boolSendY == true){
		atHome = true;
	}
}

//getEndHome toegevoegd door Jason Joshua voor het stoppen van de robot
bool getEndHome(){
	return atHome;
}