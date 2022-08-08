package ru.kofesutra.fiveten_v2.presentation.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.kofesutra.fiveten_v2.domain.AndroidDrops
import ru.kofesutra.fiveten_v2.domain.GameResult
import ru.kofesutra.fiveten_v2.domain.UserDrops
import ru.kofesutra.fiveten_v2.presentation.utils.Variables
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.liveButtonText
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.liveMessage
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListAndr

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun buttonCounts(){

        liveMessage.value = "Сделайте бросок"

        when(attemptNumber){

            // ----- Играет Юзер -----
            0 -> {
                valuesList = myValuesList
                UserDrops().firstDropUser()
                GameResult().refreshResults()
                liveMessage.value = "Ещё бросок!"
                attemptNumber = 1
            }
            1 -> {
                UserDrops().secondThirdDropsUser()
                GameResult().refreshResults()
                liveMessage.value = "И ещё один!"
                attemptNumber = 2
            }
            2 -> {
                UserDrops().secondThirdDropsUser()
                GameResult().refreshResults()
                liveButtonText.value = "Дать поиграть Андрюше"
                liveMessage.value = "Все броски сделаны"
                attemptNumber = 3
            }
            // End of ----- Играет Юзер -----
            3 -> {
                // ----- Играет Андрюша -----
                valuesList = valuesListAndr
                Variables.liveSwitchBottomSheet.value = true // Turn on bottom sheet once
                AndroidDrops().firstDropAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().andrCountTotal()
               // End of ----- Играет Андрюша -----

                GameResult().refreshResults()
                liveButtonText.value = "Бросок!"
                liveMessage.value = "Сделайте бросок"
                attemptNumber = 0
                GameResult().gameResult()
            }
        }
    } // End of buttonCounts()

} ///