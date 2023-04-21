// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
#include <ArduinoJson.h>

// Print een status- en waardetekst naar de Serial
// status -> een code die kan worden geïnterpreteerd door de HMI-applicatie
// value -> de waarde die extra context kan geven
void toJava(int status, String value){
    // Maakt een nieuw json object
    StaticJsonDocument<200> returnJson;

    // Zet de juiste waardes
    returnJson["code"] = status;
    returnJson["value"] = value;

    // Print het json object naar de Serial
    serializeJson(returnJson, Serial);
}

//Comms voorbeeld
/*
    int waarde = 420;
    toJava(200, String(420), ""); --> {"code":200,"value":"420"}

    Sring tekst = "NOODSTOP INGEDRUKT";'
    toJava(500, tekst, ""); --> {"code":500,"value":"NOODSTOP INGEDRUKT"}
*/

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