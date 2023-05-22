#include <motorController.h>
#include <slaveCurPositionController.h>

bool checkManualMove(){
    if((readY() > (returnYCor(0) - 80) && (readY() < returnYCor(0) + 200)) || (readY() > (returnYCor(1) - 80) && (readY() < returnYCor(1) + 200)) || (readY() > (returnYCor(2) - 80) && readY() < (returnYCor(2) + 200)) || (readY() > (returnYCor(3) - 80) && readY() < (returnYCor(3) + 200)) || (readY() > (returnYCor(4) - 80) && readY() > (returnYCor(4)+ 200))){
        return true;
    }else{
        return false;
    }
}