package ru.kofesutra.fiveten_v2.presentation.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.kofesutra.fiveten_v2.domain.AndroidDrops
import ru.kofesutra.fiveten_v2.domain.GameResult
import ru.kofesutra.fiveten_v2.domain.UserDrops
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.valuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.valuesListAndr

class MainViewModel(application: Application) : AndroidViewModel(application) {


    var liveMessage = MutableLiveData<String>()
    var liveButton = MutableLiveData<String>()

    fun buttonCounts(){

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
                liveButton.value = "Дать поиграть Андрюше"
                liveMessage.value = "Все броски сделаны"
                attemptNumber = 3
            }
            // End of ----- Играет Юзер -----
            3 -> {
                // ----- Играет Андрюша -----
                valuesList = valuesListAndr
                Singleton.switchBottomSheet.value = true // Turn on bottom sheet once
                AndroidDrops().firstDropAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().secondThirdDropsAndroid()
                AndroidDrops().andrCountTotal()
               // End of ----- Играет Андрюша -----

                GameResult().refreshResults()
                liveButton.value = "Бросок!"
                liveMessage.value = "Сделайте бросок"
                attemptNumber = 0
                GameResult().gameResult()
            }
        }
    } // End of buttonCounts()

} ///