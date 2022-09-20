package ru.kofesutra.fiveten_v2.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kofesutra.fiveten_v2.domain.AndroidDrops
import ru.kofesutra.fiveten_v2.domain.ResetAll
import ru.kofesutra.fiveten_v2.domain.UserDrops
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListDraw

class MainViewModel : ViewModel() {

    private var liveMessageSwitch = MutableLiveData<String>()
    val liveMessageToUI: LiveData<String> = liveMessageSwitch
    private var liveButtonText = MutableLiveData<String>()
    val liveButtonTextToUI: LiveData<String> = liveButtonText
    var liveSwitchBottomSheet = MutableLiveData<Boolean>()
    var liveSwitchBottomSheetToUI: LiveData<Boolean> = liveSwitchBottomSheet
    private var liveMyResultsNow = MutableLiveData<Int>()
    val liveMyResultsNowToUI: LiveData<Int> = liveMyResultsNow
    private var liveAndrResultNow = MutableLiveData<Int>()
    val liveAndrResultNowToUI: LiveData<Int> = liveAndrResultNow
    private var liveMyResultTotal = MutableLiveData<Int>()
    val liveMyResultTotalToUI: LiveData<Int> = liveMyResultTotal
    private var liveAndrResultTotal = MutableLiveData<Int>()
    val liveAndrResultTotalToUI: LiveData<Int> = liveAndrResultTotal
    private var liveDicesImages = MutableLiveData<List<Int>>()
    val liveDicesImagesToUI: LiveData<List<Int>> = liveDicesImages
    var liveDialogActivator = MutableLiveData<Boolean>()
    var liveDialogSwitch = 0
    var showResultsAtTheEndOfGame = "_"


    fun buttonCounts(){

        liveMessageSwitch.value = "1"

        when(attemptNumber){
            // ----- User playing -----
            0 -> {
                valuesList = myValuesList
                UserDrops().firstDropUser()
                refreshResults()
                liveMessageSwitch.value = "2"
                attemptNumber = 1
            }
            1 -> {
                UserDrops().secondThirdDropsUser()
                refreshResults()
                liveMessageSwitch.value = "3"
                attemptNumber = 2
            }
            2 -> {
                UserDrops().secondThirdDropsUser()
                refreshResults()
                liveButtonText.value = "2"
                liveMessageSwitch.value = "4"
                attemptNumber = 3
            }
            // End of ----- User playing -----
            3 -> {
                // ----- Android playing -----
                valuesList = valuesListAndr
                liveSwitchBottomSheet.value = true // Turn on bottom sheet once
                AndroidDrops().firstDropAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().andrCountTotal()
               // End of ----- Android playing -----

                refreshResults()
                liveButtonText.value = "1"
                liveMessageSwitch.value = "1"
                attemptNumber = 0
                gameResult()
            }
        }
    } // End of --- buttonCounts() ---

    fun resetAllLiveDatas() {
        ResetAll()
        liveDicesImages.value = valuesListDraw
        liveMyResultsNow.value = myResultNow
        liveAndrResultNow.value = andrResultNow
        liveMyResultTotal.value = myResultTotal
        liveAndrResultTotal.value = andrResultTotal
        liveDialogSwitch = 0
    }

    private fun refreshResults(){
        liveDicesImages.value = valuesListDraw
        liveMyResultsNow.value = myResultNow
        liveAndrResultNow.value = andrResultNow
        liveMyResultTotal.value = myResultTotal
        liveAndrResultTotal.value = andrResultTotal
    }

    private fun gameResult(){
        showResultsAtTheEndOfGame = "Game score: $myResultTotal / $andrResultTotal"
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

}