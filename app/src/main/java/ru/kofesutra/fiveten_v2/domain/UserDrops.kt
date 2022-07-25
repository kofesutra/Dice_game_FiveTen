package ru.kofesutra.fiveten_v2.domain

import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.mySummaryList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton.valuesListDraw

class UserDrops {

    fun firstDropUser() {
        for (i in 0..4) {
            val random = (1..6).shuffled()
            // если значение 5, сохраняем, если 1, то меняем на 10, остальные делаем нулями
            var randomValue: Int = random[0]
            valuesListDraw[i] = randomValue // Копируем значения в лист для отображения картинок
            when (randomValue) {
                1 -> randomValue = 10
                in 2..4 -> randomValue = 0
                6 -> randomValue = 0
            }

            myValuesList[i] = randomValue // Записываем результат в основной список valuesList
        } // for (i in 0..4)
        myResultNow = myValuesList.sum()
//        Log.d(TAG, "111 - Fun ok")
//        Log.d(TAG, "111 valuesListDraw S ${Singleton.valuesListDraw }")
//        Log.d(TAG, "111 Results S ${Singleton.myResultNow }")
    }

    fun secondThirdDropsUser(){
        val listOfNulls = mutableListOf<Int>()
        if (myValuesList.contains(0)){
            for( z in 0 until myValuesList.size) {
                val indexOfVal = myValuesList[z]
                if (indexOfVal == 0) {
                    // Добавляем в список индексов значения
                    listOfNulls.add(z)
                }
            }
            if (listOfNulls.size > 0) {
                for (i in 0 until listOfNulls.size){
                    val random = (1..6).shuffled()
                    val nullVal = listOfNulls[i]
                    var randomValue: Int = random[0]
                    valuesListDraw[nullVal] = randomValue // Копируем значения в лист для отображения картинок
                    when (randomValue) {
                        1 -> randomValue = 10
                        in 2..4 -> randomValue = 0
                        6 -> randomValue = 0
                    }
                    myValuesList[nullVal] = randomValue
                }
            }// if (valuesList.contains(0))
            myResultNow = myValuesList.sum()
        }else{
            println("Нет необходимости в броске")
        }

        if (attemptNumber == 2) {
            mySummaryList.add(0, myResultNow)
            myResultTotal = mySummaryList.sum()
        }
    }

}