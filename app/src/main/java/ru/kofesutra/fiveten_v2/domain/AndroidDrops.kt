package ru.kofesutra.fiveten_v2.domain

import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.summaryListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListDraw

class AndroidDrops {

    fun firstDropAndroid() {
        for (i in 0..4) {
            val random = (1..6).shuffled()
            var randomValue: Int = random[0]
            valuesListDraw[i] = randomValue
            when (randomValue) {
                1 -> randomValue = 10
                in 2..4 -> randomValue = 0
                6 -> randomValue = 0
            }
            valuesListAndr[i] = randomValue
        }
        andrResultNow = valuesListAndr.sum()
    }

    fun secondThirdDropsAndroid(){

        val listOfNulls = mutableListOf<Int>()

        if (valuesListAndr.contains(0)){
            for( z in 0 until valuesListAndr.size) {
                val indexOfVal = valuesListAndr[z]
                if (indexOfVal == 0) {
                    listOfNulls.add(z)
                }
            }
            if (listOfNulls.size > 0) {
                for (i in 0 until listOfNulls.size){
                    val random = (1..6).shuffled()
                    val nullVal = listOfNulls[i]
                    var randomValue: Int = random[0]
                    valuesListDraw[nullVal] = randomValue

                    when (randomValue) {
                        1 -> randomValue = 10
                        in 2..4 -> randomValue = 0
                        6 -> randomValue = 0
                    }
                    valuesListAndr[nullVal] = randomValue
                }
            }
            andrResultNow = valuesListAndr.sum()
        }else{
            println("No need for dices dropping")
        }
    }

    fun andrCountTotal(){
        summaryListAndr.add(0, andrResultNow)
        andrResultTotal = summaryListAndr.sum()
    }

}