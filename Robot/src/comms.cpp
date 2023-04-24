// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
// Arduino communicatie
#include <Wire.h>

byte i2c_rcv;

void commsSetup(){
  Serial.begin(9600);
  Wire.begin();
}

// Print een status- en waardetekst naar de Serial
// status -> een code die kan worden geïnterpreteerd door de HMI-applicatie
void toJava(int status){
  // Print de status naar serial
	Serial.println(status);
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

void toSlaveArduino(int value){
  Wire.beginTransmission(0x08);
	Wire.write(value);
	Wire.endTransmission();
}
