package ru.kofesutra.fiveten_v2.presentation.ui.howtoplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kofesutra.fiveten_v2.presentation.MainActivity
import ru.kofesutra.fiveten_v2.R
import ru.kofesutra.fiveten_v2.databinding.FragmentHowToPlayBinding

class HowToPlayFragment : Fragment() {

    private var binding: FragmentHowToPlayBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHowToPlayBinding.inflate(inflater, container,false)
        return binding!!.root

    } // End of onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Правила"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rules = """
            Правила игры:
            
            На один ход даётся три броска костей
            "5" считается как пять очков, "1" считается как десять очков, другие грани не имеют значения.
            Кубики с невыпавшими "5" и "1" перебрасываются.
            Кубики бросаются игроками по очереди.
            
            Побеждает тот игрок, который раньше наберёт 100 очков.
            В случае если игроки сделали одинаковое количество ходов побеждает игрок с большей суммой очков.
                        
            PS. Эту игру я когда-то придумал сам :)"
        """.trimIndent()

        binding?.howtoText?.text = rules
    }

    override fun onStart() {
        super.onStart()
        binding?.howBtnBack?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", "Hello")
            (activity as MainActivity).navController.navigate(R.id.action_howToPlayFragment_to_mainFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

} ///