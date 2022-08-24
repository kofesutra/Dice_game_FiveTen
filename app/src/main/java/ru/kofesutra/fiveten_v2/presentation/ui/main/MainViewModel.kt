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

class MainViewModel: ViewModel() {
    // Live Datas
    private var liveMessage = MutableLiveData<String>()
    var liveMessageToUI: LiveData<String> = liveMessage
    private var liveButtonText = MutableLiveData<String>()
    var liveButtonTextToUI: LiveData<String> = liveButtonText
    var liveSwitchBottomSheet = MutableLiveData<Boolean>()
    var liveSwitchBottomSheetToUI: LiveData<Boolean> = liveSwitchBottomSheet
    private var liveMyResultsNow = MutableLiveData<Int>()
    var liveMyResultsNowToUI: LiveData<Int> = liveMyResultsNow
    private var liveAndrResultNow = MutableLiveData<Int>()
    var liveAndrResultNowToUI: LiveData<Int> = liveAndrResultNow
    private var liveMyResultTotal = MutableLiveData<Int>()
    var liveMyResultTotalToUI: LiveData<Int> = liveMyResultTotal
    private var liveAndrResultTotal = MutableLiveData<Int>()
    var liveAndrResultTotalToUI: LiveData<Int> = liveAndrResultTotal
    private var liveDicesImages = MutableLiveData<List<Int>>()
    var liveDicesImagesToUI: LiveData<List<Int>> = liveDicesImages
    var liveDialogActivator = MutableLiveData<Boolean>()
    var liveDialogSwitch = 0
    var showResultsAtTheEndOfGame = "_"


    fun buttonCounts(){

        liveMessage.value = "Сделайте бросок"

        when(attemptNumber){
            // ----- Играет Юзер -----
            0 -> {
                valuesList = myValuesList
                UserDrops().firstDropUser()
                refreshResults()
                liveMessage.value = "Ещё бросок!"
                attemptNumber = 1
            }
            1 -> {
                UserDrops().secondThirdDropsUser()
                refreshResults()
                liveMessage.value = "И ещё один!"
                attemptNumber = 2
            }
            2 -> {
                UserDrops().secondThirdDropsUser()
                refreshResults()
                liveButtonText.value = "Дать поиграть Андрюше"
                liveMessage.value = "Все броски сделаны"
                attemptNumber = 3
            }
            // End of ----- Играет Юзер -----
            3 -> {
                // ----- Играет Андрюша -----
                valuesList = valuesListAndr
                liveSwitchBottomSheet.value = true // Turn on bottom sheet once
                AndroidDrops().firstDropAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().andrCountTotal()
               // End of ----- Играет Андрюша -----

                refreshResults()
                liveButtonText.value = "Бросок!"
                liveMessage.value = "Сделайте бросок"
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
        showResultsAtTheEndOfGame = "Счёт: $myResultTotal / $andrResultTotal"
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

} ///