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
    } // End of onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Правила"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rules = """
            Правила игры:
            
            - На один ход даётся три броска костей.
            - "5" считается как пять очков, "1" считается как десять очков, другие грани не имеют значения.
            - Кубики с невыпавшими "5" и "1" перебрасываются.
            - Кубики бросаются игроками по очереди.
            - Игроки делают одинаковое число ходов.
            - Побеждает тот игрок, который раньше наберёт 100 очков или, в случае, если все набрали 100, у кого общий итог больше.
            
            Как играть:
            
            - Нажмите кнопку "БРОСОК!"
            - Если во время следующего броска желаете перебросить какие либо кости выделите их нажатием
            - И снова кнопка "БРОСОК!"
            - После третьего броска дайте сыграть Андроиду
            
            PS. Эту игру я когда-то придумал сам
            :)
              
                            
        """.trimIndent()

        binding?.howtoText?.text = rules
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

} ///