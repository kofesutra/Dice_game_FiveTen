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
    }

    fun secondThirdDropsUser(){
        val reDropList = mutableListOf<Int>() // Создаём список индексов костей, которые надо перебросить
        val myValuesListHere = Variables.selectedDices // Создаём список самих костей, которые надо перебросить
        if (myValuesListHere.contains(1)){ // Вносим индексы перебрасываемых костей в reDropList
            for( z in 0 until myValuesListHere.size) {
                val indexOfVal = myValuesListHere[z]
                if (indexOfVal == 1) {
                    // Добавляем в список индексов значения
                    reDropList.add(z)
                }
            }

            if (reDropList.size > 0) { // Перебрасываем выбранные кости
                for (i in 0 until reDropList.size){
                    val random = (1..6).shuffled()
                    val nullVal = reDropList[i]
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
        Variables.selectedDices.clear()
        Variables.selectedDices.addAll(0, listOf(0, 0, 0, 0, 0))
    }
}