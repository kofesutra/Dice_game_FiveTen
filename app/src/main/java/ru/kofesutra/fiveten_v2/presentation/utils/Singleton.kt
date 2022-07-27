package ru.kofesutra.fiveten_v2.presentation.utils

import androidx.lifecycle.MutableLiveData

object Singleton {
    var valuesList = mutableListOf(0, 0, 0, 0, 0)
    var valuesListDraw = mutableListOf(0, 0, 0, 0, 0)
    var myValuesList = mutableListOf(0, 0, 0, 0, 0)
    var mySummaryList = mutableListOf(0)
    var myResultNow = 0
    var myResultTotal = 0
    var valuesListAndr = mutableListOf(0, 0, 0, 0, 0)
    var summaryListAndr = mutableListOf(0)
    var andrResultNow = 0
    var andrResultTotal = 0
    var attemptNumber = ButtonState().buttonState

    // Live Datas
    var liveMessage = MutableLiveData<String>()
    var liveButton = MutableLiveData<String>()

    var switchBottomSheet = MutableLiveData<Boolean>()
    var liveMyResultsNow = MutableLiveData<Int>()
    var liveAndrResultNow = MutableLiveData<Int>()
    var liveMyResultTotal = MutableLiveData<Int>()
    var liveAndrResultTotal = MutableLiveData<Int>()

    var liveDicesText = MutableLiveData<List<Int>>() // Remove at the end!!!
    var liveDicesImages = MutableLiveData<List<Int>>()

    var liveDialogActivator = MutableLiveData<Boolean>()
    var liveDialogSwitch = 0


}
