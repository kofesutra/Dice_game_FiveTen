package ru.kofesutra.fiveten_v2.presentation.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.kofesutra.fiveten_v2.domain.GameResult

class YouWin : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Вы выиграли!")
                .setMessage("Крутенько, да? ;)")
                .setPositiveButton("Зашибись!") {
                        dialog, _ ->  dialog.cancel()
                    GameResult().resetAll()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
} //