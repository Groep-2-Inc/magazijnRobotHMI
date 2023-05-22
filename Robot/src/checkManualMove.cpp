#include <motorController.h>
#include <currentPositionController.h>

bool checkManualMove(){
    if((readX() > (returnXCor(0) - 300) && (readX() < returnXCor(0) + 100)) || (readX() > (returnXCor(1) - 300) && (readX() < returnXCor(1) + 100)) || (readX() > (returnXCor(2) - 300) && readX() < (returnXCor(2) + 100)) || (readX() > (returnXCor(3) - 300) && readX() < (returnXCor(3) +100)) || (readX() > (returnXCor(4) - 300) && readX() > (returnXCor(4)+100))){
        return true;
    }else{
        return false;
    }
}