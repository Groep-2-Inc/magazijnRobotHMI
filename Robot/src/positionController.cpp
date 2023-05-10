// // Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
// #include <Arduino.h>

// // xPosSensor - Definieert de pinmode van de encoder voor de x-as.
// // yPosSensor - Definieert de pinmode van de encoder voor de y-as.
// // xPos - Slaat de waarde van de encoder voor de x-as op.
// // yPos - Slaat de waarde van de encoder voor de y-as op.
// int xPosSensor = 4;
// int yPosSensor = 2;
// int xPos = 0;
// int yPos = 0;

// // Zet de pinmode voor de encoders voor de x- en y-as.
// void positionSetup(){
//     pinMode(xPosSensor, INPUT_PULLUP);
//     pinMode(yPosSensor, INPUT_PULLUP);
// }

// // Leest de waarde van de encoder voor de x-as uit en print dit op de seriele monitor.
// int readXposition(){
//     int xPos = digitalRead(xPosSensor);

//     // Serial.print("X pos: ");
//     Serial.println(xPos);

//     return xPos;
// }

// // Leest de waarde van de encoder voor de y-as uit en print dit op de seriele monitor.
// int readYposition(){
//     int yPos = digitalRead(yPosSensor);
    
//     // Serial.print("X pos: ");
//     Serial.println(yPos);

//     return yPos;
// }