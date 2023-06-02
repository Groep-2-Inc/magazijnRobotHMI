#include <motorController.h>
#include <slaveCurPositionController.h>
#include <IRSensor.h>
#include <comms.h>

bool manualMoveZas = false;
bool sendData31 = true;
bool sendData32 = true;

//een functie die een boolean teruggeeft in de situatie op de y-as dat de robot de z-as mag bewegen 
//gebruikt de huidige y-as positie en de array met de y-waarde van het midden van elk vakje (met functie uit motorController) (Joëlle)
bool checkManualMoveYas(){
    if((readY() > (returnYCor(0) - 80) && (readY() < returnYCor(0) + 200)) || (readY() > (returnYCor(1) - 80) && (readY() < returnYCor(1) + 200)) || (readY() > (returnYCor(2) - 80) && readY() < (returnYCor(2) + 200)) || (readY() > (returnYCor(3) - 80) && readY() < (returnYCor(3) + 200)) || (readY() > (returnYCor(4) - 80) && readY() > (returnYCor(4)+ 200))){
        return true;
    }else{
        return false;
    }
}


unsigned long previousMillis = 0;

//functie die checkt of de z-as is uitgeschoven, true hij is niet uitgeschoven, dus hij mag bewogen worden naar links,rechts, omhoog, omlaag (Joëlle)
void checkManualMoveZas(){
    if(millis() - previousMillis > 10){
        if(measureZas() < 4.20){
            if(sendData31){
                sendData(31);
                sendData32 = true;
                sendData31 = false;
            }
            manualMoveZas  = true;
        }else{
            if(sendData32){
                sendData(32);
                sendData31 = true;
                sendData32 = false;
            }
            manualMoveZas = false;
        }
        previousMillis = millis();

    }

}

//functie die de het wel/niet bewegen van de z-as teruggeeft (Joëlle)
bool getManualMoveZas(){
    return manualMoveZas;
}

// functie die teruggeeft of hij mag bewegen in de box, bij true bewegen, bij false mag dat niet (Joëlle)
bool checkManualMoveBox(){
    if(((readY() > (returnYCor(0) - 80)) && readY() < (returnYCor(0) + 300)) || ((readY() > (returnYCor(1) - 80)) && readY() < (returnYCor(1) + 300)) || ((readY() > (returnYCor(2) - 80)) && readY() < (returnYCor(2) + 300)) || ((readY() > (returnYCor(3) - 80)) && readY() < (returnYCor(3) + 300)) || ((readY() > (returnYCor(4) - 80)) && readY() < (returnYCor(4) + 300))){
        return true;
        
    }else{
        return false;
    }
}