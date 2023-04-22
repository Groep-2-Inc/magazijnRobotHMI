// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>

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

		if (status == 200) {
			digitalWrite(9, HIGH);
		} else {
			digitalWrite(8, HIGH);
		}

		return status;
  }

  // Als er geen nieuwe data is return dan 
  return status;
}