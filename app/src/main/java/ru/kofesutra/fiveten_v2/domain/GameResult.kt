package ru.kofesutra.fiveten_v2.domain

import android.content.ContentValues
import android.util.Log
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.andrResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.andrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.liveAndrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.liveDialogActivator
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.liveDialogSwitch
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.mySummaryList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.summaryListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.valuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.valuesListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.valuesListDraw

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

        Singleton.liveDicesImages.value = valuesListDraw
        Singleton.liveMyResultsNow.value = myResultNow
        Singleton.liveAndrResultNow.value = andrResultNow
        Singleton.liveMyResultTotal.value = myResultTotal
        Singleton.liveAndrResultTotal.value = andrResultTotal
        liveDialogSwitch = 0
    }

    fun refreshResults(){
        Singleton.liveDicesImages.value = valuesListDraw
        Singleton.liveMyResultsNow.value = myResultNow
        Singleton.liveAndrResultNow.value = andrResultNow
        Singleton.liveMyResultTotal.value = myResultTotal
        liveAndrResultTotal.value = andrResultTotal
    }

}