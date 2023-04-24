#include <Arduino.h>

int xPosSensor = 4;
int yPosSensor = 2;
int xPos = 0;
int yPos = 0;

void positionSetup(){
    pinMode(xPosSensor, INPUT_PULLUP);
    pinMode(yPosSensor, INPUT_PULLUP);
}

int readXposition(){
    int xPos = digitalRead(xPosSensor);

    Serial.print("X pos: ");
    Serial.println(xPos);

    return xPos;
}

int readYposition(){
    int yPos = digitalRead(yPosSensor);
    
    Serial.print("X pos: ");
    Serial.println(yPos);

    return yPos;
}