package ru.kofesutra.fiveten_v2.domain

import ru.kofesutra.fiveten_v2.presentation.utils.Variables
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.mySummaryList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListDraw

class UserDrops {

    fun firstDropUser() {
        for (i in 0..4) {
            val random = (1..6).shuffled()
            // If result is 5 save it, if result is 1 change it to 10, others are 0
            var randomValue: Int = random[0]
            valuesListDraw[i] = randomValue // Copying to images list
            when (randomValue) {
                1 -> randomValue = 10
                in 2..4 -> randomValue = 0
                6 -> randomValue = 0
            }
            myValuesList[i] = randomValue // Saving results to valuesList
        } // for (i in 0..4)
        myResultNow = myValuesList.sum()
    }

    fun secondThirdDropsUser(){
        val reDropIndexesList = mutableListOf<Int>() // Creating list of the indexes of the dices to reroll
        val reDropDicesList = Variables.selectedDices // Creating list of dices to reroll
        if (reDropDicesList.contains(1)){ // Saving indexes to the reDropList
            for( z in 0 until reDropDicesList.size) {
                val indexOfVal = reDropDicesList[z]
                if (indexOfVal == 1) {
                    reDropIndexesList.add(z)
                }
            }

            if (reDropIndexesList.size > 0) { // Reroll selected dices
                for (i in 0 until reDropIndexesList.size){
                    val random = (1..6).shuffled()
                    val nullVal = reDropIndexesList[i]
                    var randomValue: Int = random[0]
                    valuesListDraw[nullVal] = randomValue // Copying to the images list
                    when (randomValue) {
                        1 -> randomValue = 10
                        in 2..4 -> randomValue = 0
                        6 -> randomValue = 0
                    }
                    myValuesList[nullVal] = randomValue
                }
            }
            myResultNow = myValuesList.sum()
            Variables.selectedDices.clear() // Clearing list of the rerolling
            Variables.selectedDices.addAll(0, listOf(0, 0, 0, 0, 0))
        }else{
            println("No need to reroll")
        }

        if (attemptNumber == 2) {
            mySummaryList.add(0, myResultNow)
            myResultTotal = mySummaryList.sum()
        }
    } // fun secondThirdDropsUser()
}