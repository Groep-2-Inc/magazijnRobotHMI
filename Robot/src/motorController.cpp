// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <joystick.h>
#include <comms.h>
#include <endStop.h>

// globalSpeed - Definieert de snelheid van de robot.
// directionPinX - Definieerd de pinmode van de beweging op de x-as.
// directionPinY - Definieerd de pinmode van de beweging op op de y-as.
// pwmPinX - Definieerd de pinmode van de motor voor de x-as.
// pwmPinY - Definieerd de pinmode van de motor voor de y-as.
// brakePinX - Definieerd de pinmode van de rem van de motor voor de x-as.
// brakePinY - Definieerd de pinmode van de rem van de motor voor de y-as.
int globalSpeed = 255;
const int directionPinX = 13;
const int pwmPinX = 11;
const int directionPinY = 12;
const int pwmPinY = 3;
const int brakePinX = 8;
const int brakePinY = 9;

// Zet de pinmode van de bovenstaande variabelen.
void motorSetup() {
    pinMode(directionPinX, OUTPUT);
    pinMode(pwmPinX, OUTPUT);
    pinMode(directionPinY, OUTPUT);
    pinMode(pwmPinY, OUTPUT);
    pinMode(brakePinX, OUTPUT);
    pinMode(brakePinY, OUTPUT);
}

// Zorgt ervoor dat de robot omhoog beweegt wanneer deze functie wordt aangeroepen.
void moveUp(){
    digitalWrite(directionPinY, LOW);
    digitalWrite(brakePinY, LOW);

    analogWrite(pwmPinY, globalSpeed);
}

// Zorgt ervoor dat de robot omlaag beweegt wanneer deze functie wordt aangeroepen.
void moveDown(){
    digitalWrite(directionPinY, HIGH);
    digitalWrite(brakePinY, LOW);

    analogWrite(pwmPinY, globalSpeed);
}

// Zorgt ervoor dat de robot naar links beweegt wanneer deze functie wordt aangeroepen.
void moveLeft(){
    digitalWrite(directionPinX, LOW);
    digitalWrite(brakePinX, LOW);

    analogWrite(pwmPinX, globalSpeed);
}

// Zorgt ervoor dat de robot naar rechts beweegt wanneer deze functie wordt aangeroepen.
void moveRight(){
    digitalWrite(directionPinX, HIGH);
    digitalWrite(brakePinX, LOW);

    analogWrite(pwmPinX, globalSpeed);
}

// Zorgt ervoor dat de robot direct stop wanneer deze functie wordt aangeroepen.
void stopMovement(){
    digitalWrite(brakePinX, HIGH);
    digitalWrite(brakePinY, HIGH);
    analogWrite(pwmPinX, 255);
    analogWrite(pwmPinY, 255);
}

// Deze functie zorgt ervoor dat de robot manueel gebruikt kan worden.
void manualControl(){
    // resetEndStop();
    // Leest waarde van de joystick uit en maakt hier een string van.
    String dir = readJoystick();

    // Serial.println(dir);

    // Wanneer de waarde 1.0.0 is
    if (dir == "1.0.0"){
        // Beweeg naar links
        moveLeft();
    // Wanneer de waarde 2.0.0 is
    }else if (dir == "2.0.0"){
        // Beweeg naar rechts
        moveRight();
    // Wanner de waarde 0.1.0 is
    }else if (dir == "0.1.0"){
        // Beweeg omhoog
        moveUp();
    // Wanneer de waarde 0.2.0 is
    }else if (dir == "0.2.0"){
        // Beweeg omlaag
        moveDown();
    //Wanneer de waarde 1.1.0 is
    }else if (dir == "1.1.0"){
        // Beweeg naar linksboven
        moveUp();
        moveLeft();
    // Wanneer de waarde 1.2.0 is
    }else if (dir == "1.2.0"){
        // Beweeg naar linksonder
        moveDown();
        moveLeft();
    // Wanneer de waarde 2.1.0 is
    }else if (dir == "2.1.0"){
        // Beweeg naar rechtsboven
        moveUp();
        moveRight();
    // Wanneer de waarde 2.2.0 is
    }else if (dir == "2.2.0"){
        // Beweeg naar rechtsonder
        moveDown();
        moveRight();
    // Wanneer de waarde 0.0.1 is
    }else if (dir == "0.0.1"){
        // Beweeg de z-as naar voren en stopt andere bewegingen
        toSlaveArduino(1);
        stopMovement();
    // Wanneer de waarde 0.0.2 is
    }else if (dir == "0.0.2"){
        // Beweeg de z-as naar achter en stopt andere bewegingen
        toSlaveArduino(2);
        stopMovement();
    // Anders stop alle bewegingen
    }else {
        toSlaveArduino(0);
        stopMovement();
    }
}