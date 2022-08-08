package ru.kofesutra.fiveten_v2.presentation.utils

import androidx.lifecycle.MutableLiveData

object Variables {
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
    var liveButtonText = MutableLiveData<String>()
    var liveSwitchBottomSheet = MutableLiveData<Boolean>()
    var liveMyResultsNow = MutableLiveData<Int>()
    var liveAndrResultNow = MutableLiveData<Int>()
    var liveMyResultTotal = MutableLiveData<Int>()
    var liveAndrResultTotal = MutableLiveData<Int>()
    var liveDicesImages = MutableLiveData<List<Int>>()
    var liveDialogActivator = MutableLiveData<Boolean>()
    var liveDialogSwitch = 0


}
