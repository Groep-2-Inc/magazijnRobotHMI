// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
// Arduino communicatie
#include <Wire.h>

byte i2c_rcv;

// Start serial zodat deze in elk ander bestand gebruikt kan worden.
// Start de communicatie tussen de arduino's.
int recieved = 0;

void receiveEvent(int bytes){
  recieved = Wire.read();
}

void commsSetup(){
  Serial.begin(9600);
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
}

// Print een status- en waardetekst naar de Serial
// status -> een code die kan worden geïnterpreteerd door de HMI-applicatie
void toJava(int status){
  // Print de status naar serial
	// Serial.println(status);
}

// Haalt status codes uit de Serial.
int fromJava() {
  int status = 0;
  if (Serial.available() > 0) {
		// Leest de bytes van de seriële poort
		status = Serial.readString().toInt();

		return status;
  }

  // Als er geen nieuwe data is return dan 
  return status;
}

// Zorgt ervoor dat data vanuit de master arduino naar de slave arduino gestuurd wordt
void toSlaveArduino(int value){
  // Serial.println(value);
  Wire.beginTransmission(9);
	Wire.write(value);
	Wire.endTransmission();
}

int getFromSlave(){
  return recieved;
}
