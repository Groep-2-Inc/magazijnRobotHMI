// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <joystick.h>
#include <comms.h>
#include <endStop.h>
#include <currentPositionController.h>
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

const int brakePinX = 8;

const int xCor[7]{0, 1400, 2800, 4055, 5550, 7600, 8600};

// Zorgt ervoor dat de robot direct stop wanneer deze functie wordt aangeroepen.
void stopMovement(){
    digitalWrite(brakePinX, HIGH);
    analogWrite(pwmPinX, 255);
}

// Zet de pinmode van de bovenstaande variabelen.
void motorSetup() {
    pinMode(directionPinX, OUTPUT);
    pinMode(pwmPinX, OUTPUT);
    pinMode(brakePinX, OUTPUT);
}

// Zorgt ervoor dat de robot naar links beweegt wanneer deze functie wordt aangeroepen. aangepast met ifstatement door Jason Joshua
void moveLeft(){
    if(checkEndStopX() != true){
    digitalWrite(directionPinX, LOW);
    digitalWrite(brakePinX, LOW);
    analogWrite(pwmPinX, globalSpeed);
    } else{
        stopMovement();
    }
}

// Zorgt ervoor dat de robot naar rechts beweegt wanneer deze functie wordt aangeroepen. aangepast met ifstatement door Jason Joshua
void moveRight(){
    if(readX() < 9000){
    digitalWrite(directionPinX, HIGH);
    digitalWrite(brakePinX, LOW);
    analogWrite(pwmPinX, globalSpeed);
    }else{
        stopMovement();
    }
}

//move down functie die het juiste naar de slave stuurt, ivm end stops, door Jason Joshua
void moveDown(){
    if(checkEndStopY() != true){
        toSlaveArduino(4);
    } else{
        toSlaveArduino(0);
    }
}

bool hasMoved = false;
bool moveX (int coordinate){
    coordinate = coordinate - 1;
    if (xCor[coordinate] > readX() && !hasMoved){
        moveRight();
    }else if (xCor[coordinate] < readX() && !hasMoved){
        moveLeft();
    } else {
        hasMoved = true;
        return true;
    }
    return false;
}

bool moveY (int coordinate){
    toSlaveArduino(coordinate + 5);
    if(getFromSlave() == 101){
        return true;
    } else {
        return false;
    }
}

bool boolY = false;
bool boolX = false;
bool moveXY(int x, int y){
    if(boolY == false){
        boolY = moveY(y);
    }
    if(boolX == false){
        boolX = moveX(x);
    }
    return boolY && boolX;
}

void resetBoolXY(){
    boolY = false;
    boolX = false;
}

void moveToHome(){
    while(!checkEndStopY() || !checkEndStopX()){
        moveLeft();
        moveDown();
    }
}

void resetHasMoved(){
  hasMoved = false;
}

bool returnHasMoved(){
    return hasMoved;
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
        toSlaveArduino(3);
    // Wanneer de waarde 0.2.0 is
    }else if (dir == "0.2.0"){
        // Beweeg omlaag
        moveDown();
    //Wanneer de waarde 1.1.0 is
    }else if (dir == "1.1.0"){
        // Beweeg naar linksboven
        toSlaveArduino(3);
        moveLeft();
    // Wanneer de waarde 1.2.0 is
    }else if (dir == "1.2.0"){
        // Beweeg naar linksonder
        moveDown();
        moveLeft();
    // Wanneer de waarde 2.1.0 is
    }else if (dir == "2.1.0"){
        // Beweeg naar rechtsboven
        toSlaveArduino(3);
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