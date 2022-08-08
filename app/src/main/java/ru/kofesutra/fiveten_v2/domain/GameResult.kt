package ru.kofesutra.fiveten_v2.domain

import ru.kofesutra.fiveten_v2.presentation.utils.Variables
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.liveAndrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.liveDialogActivator
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.liveDialogSwitch
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.mySummaryList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.summaryListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListDraw

class GameResult {

    fun gameResult(){
        if (myResultTotal > 99 || andrResultTotal > 99){
            if (myResultTotal > andrResultTotal) { // YouWin
                liveDialogSwitch = 1
                liveDialogActivator.value = true
            } else if (myResultTotal < andrResultTotal) // YouLoose
            {
                liveDialogSwitch = 2
                liveDialogActivator.value = true
            } else if (myResultTotal == andrResultTotal) // WinWin
            {
                liveDialogSwitch = 3
                liveDialogActivator.value = true
            }
        }
    }

     fun resetAll(){
        attemptNumber = 0
        valuesList = mutableListOf(0, 0, 0, 0, 0)
        myValuesList = mutableListOf(0, 0, 0, 0, 0)
        valuesListDraw = mutableListOf(0, 0, 0, 0, 0)
        myResultNow = 0
        myResultTotal = 0
        valuesListAndr = mutableListOf(0, 0, 0, 0, 0)
        andrResultNow = 0
        andrResultTotal = 0
        mySummaryList = mutableListOf(0)
        summaryListAndr = mutableListOf(0)

        Variables.liveDicesImages.value = valuesListDraw
        Variables.liveMyResultsNow.value = myResultNow
        Variables.liveAndrResultNow.value = andrResultNow
        Variables.liveMyResultTotal.value = myResultTotal
        liveAndrResultTotal.value = andrResultTotal
        liveDialogSwitch = 0
    }

    fun refreshResults(){
        Variables.liveDicesImages.value = valuesListDraw
        Variables.liveMyResultsNow.value = myResultNow
        Variables.liveAndrResultNow.value = andrResultNow
        Variables.liveMyResultTotal.value = myResultTotal
        liveAndrResultTotal.value = andrResultTotal
    }

}