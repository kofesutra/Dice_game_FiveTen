package ru.kofesutra.fiveten_v2.presentation.ui.howtoplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kofesutra.fiveten_v2.databinding.FragmentHowToPlayBinding

class HowToPlayFragment : Fragment() {

    private var binding: FragmentHowToPlayBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHowToPlayBinding.inflate(inflater, container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Rules"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rules = """
                Rules of the game:
            
               - Three throws of the dice are given per turn.
               - "5" counts as five points, "1" counts as ten points, the rest of the faces do not matter.
               - Dice with "5" and "1" are being rerolled.
               - Players take turns throwing the dice.
               - Players make the same amount of turns.
               - The winner is the player who reaches 100 points first or, if everyone reaches 100 points, the player with the biggest amount of points.
            
               How to play:
            
               - Press the button "ROLL!"
               - If you want to reroll any dice on the next roll, select them by pressing
               - And again the button "ROLL!"
               - After the third throw, let the Android play
            
               PS. I invented this game myself
               :)
              
                            
        """.trimIndent()

        binding?.howtoText?.text = rules
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}