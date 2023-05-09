#include <Arduino.h>
#include <comms.h>

const int encoderPinX = 2;
unsigned int xPosition = 0;

void changeX(){
    if(digitalRead(13) == HIGH)
        xPosition +=1;
    else if (digitalRead(13) == LOW && xPosition != 0){
        xPosition -=1;
    }
}

void encoderSetup(){
    pinMode(encoderPinX, INPUT_PULLUP);
    attachInterrupt(digitalPinToInterrupt(encoderPinX), changeX, CHANGE);
}


int readX(){
    return xPosition;
}