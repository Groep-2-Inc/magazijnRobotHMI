// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
// Arduino communicatie
#include <Wire.h>
// Noodstop
#include <emergencyStop.h>

int recieved = 0;
bool hasConection = false;
int incomingData = 0;
unsigned long commsStartTime = 0; // int die bijhoud wanneer er voor het eerste verbinding is gemaakt

//voeg een recieve event toe voor de wire  (Door Jason Joshua)
void receiveEvent(int bytes){
  recieved = Wire.read();
}

// Start serial zodat deze in elk ander bestand gebruikt kan worden.
// Start de communicatie tussen de arduino's.
void commsSetup(){
  Serial.begin(9600);
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
}

// Print een status- en waardetekst naar de Serial
// status -> een code die kan worden geïnterpreteerd door de HMI-applicatie
void toJava(int status){
  // Print de status naar serial
	Serial.println(status);
}

// Haalt data op vanuit Java
int fromJava() {
	// Als er data beschkikbaar is
	if(Serial.available() > 0) {
		// Lees de Serial uit en zet de String om naar een int
		incomingData = Serial.readString().toInt();

		// Op basis va de data
		switch (incomingData){
			// Maak verbinding met de HMI
			case 50:
				// Verstuur code 200
				toJava(200);
				// Sla de huidge tijd op in commsStartTime
				// Is nodig zodat de robot even wacht met homen na het ontvangen van een verbindings request
				commsStartTime = millis();
				// Zet communication op true
        		hasConection = true;
				break;
			case 500:
				// Als hij 500 ontvangt vanuit 
				// Activeer de noodstop
				emgStop();
			default:
				break;
		}
	}

  // Als er nieuwe data is return dan incomingData
  return incomingData;
}

// Zorgt ervoor dat data vanuit de master arduino naar de slave arduino gestuurd wordt
void toSlaveArduino(int value){
 	Wire.beginTransmission(9);
	Wire.write(value);
	Wire.endTransmission();
}

//return de recieved int van de slave (Door Jason Joshua)
int getFromSlave(){
  return recieved;
}

bool getConection(){
	return hasConection;
}

// Returnt de waarde van de commsStartTime
unsigned long getCommsStartTime(){
	return commsStartTime;
}