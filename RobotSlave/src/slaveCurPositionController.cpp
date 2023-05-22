#include <Arduino.h>

//definieer de encode pins, int om de y positie bij te houden  (door Jason Joshua)
const int encoderPinY = 2;
unsigned int yPosition = 0;

//functie om de ypositie bij te werken
void changeY(){
    if(digitalRead(12) == LOW)
        yPosition +=1;
    else if (digitalRead(12) == HIGH && yPosition != 0){
        yPosition -=1;
    }
}

//setup om de encoder te laten werken  (door Jason Joshua)
void encoderSetup(){
    pinMode(encoderPinY, INPUT_PULLUP);
    attachInterrupt(digitalPinToInterrupt(encoderPinY), changeY, CHANGE);
}

//vunctie die de current y position returned (door Jason Joshua)
int readY(){
    return yPosition;
}