package ru.kofesutra.fiveten_v2.presentation.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kofesutra.fiveten_v2.R

class BottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = activity?.layoutInflater?.inflate(R.layout.bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Settings (Behavior)
        val offsetFromTop = 150 // Отступ сверху
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = false
            isCancelable = true
//            isDraggable = true
//            isHideable = true
            expandedOffset = offsetFromTop // Отступ сверху
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

}