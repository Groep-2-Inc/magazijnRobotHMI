#include <Arduino.h>
#include <comms.h>

//definieer de x encoder pin en de int om de x positie bij te houden (door Jason Joshua)
const int encoderPinX = 2;
unsigned int xPosition = 0;

//functie om de xPosition bij te werken bij het updaten van de encoder  (door Jason Joshua)
void changeX(){
    if(digitalRead(13) == HIGH)
        xPosition +=1;
    else if (digitalRead(13) == LOW && xPosition != 0){
        xPosition -=1;
    }
}

//setup om de encoder te laten werken   (door Jason Joshua)
void encoderSetup(){
    pinMode(encoderPinX, INPUT_PULLUP);
    attachInterrupt(digitalPinToInterrupt(encoderPinX), changeX, CHANGE);
}

//functie die de x coordinaat returned   (door Jason Joshua)
int readX(){
    return xPosition;
}