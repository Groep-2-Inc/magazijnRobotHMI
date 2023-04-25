#include <Arduino.h>
#include <motorController.h>

const int endStopPinX = 2;
const int endStopPinY = 4;
int endStopValueX = 0;
int endStopValueY = 0;
bool atHome = false;

void endStopSetup(){
    pinMode(endStopPinX, OUTPUT);
    pinMode(endStopPinY, OUTPUT);
}

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

void resetEndStop(){
	atHome = false;
}

void readEndStop(){
    if (checkEndStopX() && checkEndStopY() && !atHome){
        atHome = true;
        stopMovement();
        Serial.println("De encoders zijn gereset");
    }
}