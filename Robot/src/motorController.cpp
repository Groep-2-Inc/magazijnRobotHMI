// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>
#include <joystick.h>
#include <comms.h>
#include <endStop.h>
#include <currentPositionController.h>
#include <endStop.h>
#include <checkManualMove.h>

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

const int xCor[7]{0, 1420, 2820, 4075, 5570, 7620, 8620};

// functie de met een gegeven nummer, de waarde van die plek uit de array haalt
int returnXCor(int number){
    return xCor[number];
}

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
    // Serial.println("Links");
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
    // Serial.println("Rechts");
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

//functie voor het oppakken van een product (Door Jason Joshua)
bool pickUpProduct(){
    toSlaveArduino(12);
}

//move x naar een specefieke coordinaat, returned true als die naar het coordinaat is en stopt dan met bewegen  (Door Jason Joshua)
bool hasMoved = false;
bool moveX (int coordinate){
    coordinate = coordinate - 1;
    if (xCor[coordinate] > readX() && !hasMoved){
        moveRight();
    }else if (xCor[coordinate] < readX() && !hasMoved){
        moveLeft();
    } else {
        hasMoved = true;
        stopMovement();
        return true;
    }
    return false;
}

//move y naar een specefieke coordinaat, returned true als die naar het coordinaat is en stopt dan met bewegen  (Door Jason Joshua)
bool moveY (int coordinate){
    toSlaveArduino(coordinate + 5);
    if(getFromSlave() == 101){
        toSlaveArduino(0);
        return true;
    } else {
        return false;
    }
}

//kijk of de robot bij de y en x positie is  (Door Jason Joshua)
bool boolY = false;
bool boolX = false;
bool moveXY(int x, int y){
    if(!boolY){
        boolY = moveY(y);
    }
    if(!boolX){
        boolX = moveX(x);
    }
    return boolY && boolX;
}

//reset de bools  (Door Jason Joshua) (alvast voor het resetten van de robot voor het oppakken van meerdere producten)
void resetBoolXY(){
    boolY = false;
    boolX = false;
}

//functie waarmee de robot homed   (Door Jason Joshua)
bool moveToHome(){
    if(!checkEndStopY()){
        moveDown();
    } else if (checkEndStopY()){
        toSlaveArduino(0);
    }
    if(!checkEndStopX()){
        moveLeft();
    } else  if (checkEndStopX()){
        stopMovement();
    }  

    updateAtHome();
    return(getEndHome());
}

//reset de hasmoved, alvast voor het resetten voor het pakken van meerdere producten   (Door Jason Joshua)
void resetHasMoved(){
  hasMoved = false;
}

//return de hasmoved bool   (Door Jason Joshua)
bool returnHasMoved(){
    return hasMoved;
}

//krijg de x coordinaat door middel van de statuscode  (Door Jason Joshua)
int getCorX(int serialmessage){
    switch(serialmessage){
        case 401: case 411: case 421: case 431: case 441:
            return 1;
            break;
        case 402: case 412: case 422: case 432: case 442:
            return 2;
            break;
        case 403: case 413: case 423: case 433: case 443:
            return 3;
            break;
        case 404: case 414: case 424: case 434: case 444:
            return 4;
            break;
        case 405: case 415: case 425: case 435: case 445:
            return 5;
            break;
        default:
            return 0;
            break;
    }
}

//krijg de y coordinaat door middel van de statuscode  (Door Jason Joshua)
int getCorY(int serialmessage){
    switch(serialmessage){
        case 401: case 402: case 403: case 404: case 405:
            return 1;
            break;
        case 411: case 412: case 413: case 414: case 415:
            return 2;
            break;
        case 421: case 422: case 423: case 424: case 425:
            return 3;
            break;
        case 431: case 432: case 433: case 434: case 435:
            return 4;
            break;
        case 441: case 442: case 443: case 444: case 445:
            return 5;
            break;
        default:
            return 0;
            break;
    }
}

// Deze functie zorgt ervoor dat de robot manueel gebruikt kan worden.
void manualControl(){
    toSlaveArduino(22);
    // resetEndStop();
    // Leest waarde van de joystick uit en maakt hier een string van.
    String dir = readJoystick();

    // Serial.println(dir);

   
    // Wanneer de waarde 1.0.0 is
    if (dir == "1.0.0"){
        // Beweeg naar links
        if(getCanMoveInBox()){
            moveLeft();
        }
    // Wanneer de waarde 2.0.0 is
    }else if (dir == "2.0.0"){
        // Beweeg naar rechts
        if(getCanMoveInBox()){
            moveRight(); 
        }       
    // Wanner de waarde 0.1.0 is
    }else if (dir == "0.1.0"){
        // Beweeg omhoog
        toSlaveArduino(3);
    // Wanneer de waarde 0.2.0 is
    }else if (dir == "0.2.0"){
        if(getCanMoveInBox()){
        // Beweeg omlaag
            moveDown();
        }
    //Wanneer de waarde 1.1.0 is
    }else if (dir == "1.1.0"){
        // Beweeg naar linksboven
        if(getCanMoveInBox()){
            toSlaveArduino(3);
            moveLeft();
        }
    // Wanneer de waarde 1.2.0 is
    }else if (dir == "1.2.0"){
        // Beweeg naar linksonder
        if(getCanMoveInBox()){
            moveDown();
            moveLeft();
        }
    // Wanneer de waarde 2.1.0 is
    }else if (dir == "2.1.0"){
        // Beweeg naar rechtsboven
        if(getCanMoveInBox()){
            toSlaveArduino(3);
            moveRight();
        }
    // Wanneer de waarde 2.2.0 is
    }else if (dir == "2.2.0"){
        // Beweeg naar rechtsonder
        if(getCanMoveInBox()){
            moveDown();
            moveRight();
        }
    // Wanneer de waarde 0.0.1 is
    }else if (dir == "0.0.1"){
        // Beweeg de z-as naar voren als dat mogelijk is (en hij dus niet de stelling omstoot (Joëlle)) en stopt andere bewegingen
        if(checkManualMove()){
            toSlaveArduino(1);
            stopMovement();
        }
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