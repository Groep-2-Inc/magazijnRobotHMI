#include <Arduino.h>

const int encoderPinY = 2;
unsigned int yPosition = 0;

void changeY(){
    if(digitalRead(12) == LOW)
        yPosition +=1;
    else if (digitalRead(12) == HIGH && yPosition != 0){
        yPosition -=1;
    }
}

void encoderSetup(){
    pinMode(encoderPinY, INPUT_PULLUP);
    attachInterrupt(digitalPinToInterrupt(encoderPinY), changeY, CHANGE);
}


int readY(){
    return yPosition;
}