package ru.kofesutra.fiveten_v2.domain

import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.andrResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.attemptNumber
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultNow
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myResultTotal
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.mySummaryList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.myValuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.summaryListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesList
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListAndr
import ru.kofesutra.fiveten_v2.presentation.utils.Variables.valuesListDraw

class ResetAll {
     init{
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
    }
}