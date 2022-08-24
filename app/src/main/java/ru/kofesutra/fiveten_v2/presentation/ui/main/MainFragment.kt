package ru.kofesutra.fiveten_v2.presentation.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import ru.kofesutra.fiveten_v2.R
import ru.kofesutra.fiveten_v2.databinding.FragmentMainBinding
import ru.kofesutra.fiveten_v2.presentation.MainActivity
import ru.kofesutra.fiveten_v2.presentation.utils.BottomSheet
import ru.kofesutra.fiveten_v2.presentation.utils.Variables

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mViewModel: MainViewModel by activityViewModels()
    private var selectDiceReDrawOff = 0
    private var selectDiceReDrawOn = 0
//    private val gameResultForDialogFragment = "xxxxx"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    } // End of --- onCreateView ---

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Не отображать Back Button на ActionBar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = "FiveTen_v2"

        // === OptionsMenu ===
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_rules -> {
                        (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_howToPlayFragment)
                        return true
                    }
                    R.id.action_exit -> {
                        Toast.makeText(context, "До встречи! ;)", Toast.LENGTH_LONG).show()
                        activity!!.finish()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // End of === OptionsMenu ===

        addObservers()

        binding?.myScoresNow?.text = "0"
        binding?.myScoresTotal?.text = "0"
        binding?.andrScoresNow?.text = "0"
        binding?.andrScoresTotal?.text = "0"
        binding?.message1?.text = "Сделайте бросок"

        // --- Start the game ---
        binding?.playButton?.setOnClickListener {
            mViewModel.buttonCounts()
        }
        // End of --- Start the game ---

        // --- Выбор костей для переброски ---
        binding?.dice1Draw?.setOnClickListener {
            val indexOfDice = 0
            selectDiceImagesToDraw(indexOfDice)
                when (Variables.selectedDices[indexOfDice]) {
                    0 -> {
                        Variables.selectedDices[indexOfDice] = 1
                        binding?.dice1Draw?.setImageResource(selectDiceReDrawOn)
                    }
                    1 -> {
                        Variables.selectedDices[indexOfDice] = 0
                        binding?.dice1Draw?.setImageResource(selectDiceReDrawOff)
                    }
                }
        }
        binding?.dice2Draw?.setOnClickListener {
            val indexOfDice = 1
            selectDiceImagesToDraw(indexOfDice)
            when (Variables.selectedDices[indexOfDice]) {
                0 -> {
                    Variables.selectedDices[indexOfDice] = 1
                    binding?.dice2Draw?.setImageResource(selectDiceReDrawOn)
                }
                1 -> {
                    Variables.selectedDices[indexOfDice] = 0
                    binding?.dice2Draw?.setImageResource(selectDiceReDrawOff)
                }
            }
        }
        binding?.dice3Draw?.setOnClickListener {
            val indexOfDice = 2
            selectDiceImagesToDraw(indexOfDice)
            when (Variables.selectedDices[indexOfDice]) {
                0 -> {
                    Variables.selectedDices[indexOfDice] = 1
                    binding?.dice3Draw?.setImageResource(selectDiceReDrawOn)
                }
                1 -> {
                    Variables.selectedDices[indexOfDice] = 0
                    binding?.dice3Draw?.setImageResource(selectDiceReDrawOff)
                }
            }
        }
        binding?.dice4Draw?.setOnClickListener {
            val indexOfDice = 3
            selectDiceImagesToDraw(indexOfDice)
            when (Variables.selectedDices[indexOfDice]) {
                0 -> {
                    Variables.selectedDices[indexOfDice] = 1
                    binding?.dice4Draw?.setImageResource(selectDiceReDrawOn)
                }
                1 -> {
                    Variables.selectedDices[indexOfDice] = 0
                    binding?.dice4Draw?.setImageResource(selectDiceReDrawOff)
                }
            }
        }
        binding?.dice5Draw?.setOnClickListener {
            val indexOfDice = 4
            selectDiceImagesToDraw(indexOfDice)
            when (Variables.selectedDices[indexOfDice]) {
                0 -> {
                    Variables.selectedDices[indexOfDice] = 1
                    binding?.dice5Draw?.setImageResource(selectDiceReDrawOn)
                }
                1 -> {
                    Variables.selectedDices[indexOfDice] = 0
                    binding?.dice5Draw?.setImageResource(selectDiceReDrawOff)
                }
            }
        }
        // End of --- Выбор костей для переброски ---

    } // End of onViewCreated

    private fun selectDiceImagesToDraw(index: Int) {
        when (Variables.valuesListDraw[index]) {
            1 -> {
                selectDiceReDrawOff = R.drawable.dd1r80
                selectDiceReDrawOn = R.drawable.dd1r80redrop
            }
            2 -> {
                selectDiceReDrawOff = R.drawable.dd2r80
                selectDiceReDrawOn = R.drawable.dd2r80redrop
            }
            3 -> {
                selectDiceReDrawOff = R.drawable.dd3r80
                selectDiceReDrawOn = R.drawable.dd3r80redrop
            }
            4 -> {
                selectDiceReDrawOff = R.drawable.dd4r80
                selectDiceReDrawOn = R.drawable.dd4r80redrop
            }
            5 -> {
                selectDiceReDrawOff = R.drawable.dd5r80
                selectDiceReDrawOn = R.drawable.dd5r80redrop
            }
            6 -> {
                selectDiceReDrawOff = R.drawable.dd6r80
                selectDiceReDrawOn = R.drawable.dd6r80redrop
            }
        }
    } // End of --- selectDiceImagesToDraw ---

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun runBottomSheet() {
        val bottomSheet = BottomSheet()
        bottomSheet.show(parentFragmentManager, BottomSheet.TAG)

        Handler(Looper.getMainLooper()).postDelayed({
            bottomSheet.dismiss() // закрыть окно..
        }, 2000) // .. после 2 секундной задержки
    }

    private fun bindDicesImages() {
        // ----- Заполнение картинками -----
        var diceDrawTemp = 0
        for (i in 0..4) {
            when (Variables.valuesListDraw[i]) {
                1 -> diceDrawTemp = R.drawable.dd1r80
                2 -> diceDrawTemp = R.drawable.dd2r80
                3 -> diceDrawTemp = R.drawable.dd3r80
                4 -> diceDrawTemp = R.drawable.dd4r80
                5 -> diceDrawTemp = R.drawable.dd5r80
                6 -> diceDrawTemp = R.drawable.dd6r80
            }
            when (i) {
                0 -> binding?.dice1Draw?.setImageResource(diceDrawTemp)
                1 -> binding?.dice2Draw?.setImageResource(diceDrawTemp)
                2 -> binding?.dice3Draw?.setImageResource(diceDrawTemp)
                3 -> binding?.dice4Draw?.setImageResource(diceDrawTemp)
                4 -> binding?.dice5Draw?.setImageResource(diceDrawTemp)
            }
        }
    } // End of ----- Заполнение картинками -----

    private fun runGameEndDialog(result: String) {
        when (mViewModel.liveDialogSwitch) {
            1 -> {
                val bundle = bundleOf("gameResultForDialogFragment" to result)
                view?.findNavController()?.navigate(R.id.action_mainFragment_to_youWin, bundle)
                mViewModel.liveDialogSwitch = 0
                mViewModel.resetAllLiveDatas()
            }
            2 -> {
                val bundle = bundleOf("gameResultForDialogFragment" to result)
                view?.findNavController()?.navigate(R.id.action_mainFragment_to_youLoose, bundle)
                mViewModel.liveDialogSwitch = 0
                mViewModel.resetAllLiveDatas()
            }
            3 -> {
                val bundle = bundleOf("gameResultForDialogFragment" to result)
                view?.findNavController()?.navigate(R.id.action_mainFragment_to_winWin, bundle)
                mViewModel.liveDialogSwitch = 0
                mViewModel.resetAllLiveDatas()
            }
        }
    } // End of --- runGameEndDialog() ---

    private fun addObservers() {
        mViewModel.liveMessageToUI.observe(viewLifecycleOwner) {
            binding!!.message1.text = it
        }
        mViewModel.liveButtonTextToUI.observe(viewLifecycleOwner) {
            binding!!.playButton.text = it
        }
        mViewModel.liveSwitchBottomSheetToUI.observe(viewLifecycleOwner) {
            if (it == true) {
                runBottomSheet()
                mViewModel.liveSwitchBottomSheet.value = false
            }
        }
        mViewModel.liveMyResultsNowToUI.observe(viewLifecycleOwner) {
            binding!!.myScoresNow.text = it.toString()
        }
        mViewModel.liveAndrResultNowToUI.observe(viewLifecycleOwner) {
            binding!!.andrScoresNow.text = it.toString()
        }
        mViewModel.liveMyResultTotalToUI.observe(viewLifecycleOwner) {
            binding!!.myScoresTotal.text = it.toString()
        }
        mViewModel.liveAndrResultTotalToUI.observe(viewLifecycleOwner) {
            binding!!.andrScoresTotal.text = it.toString()
        }
        mViewModel.liveDicesImagesToUI.observe(viewLifecycleOwner) {
            bindDicesImages()
        }
        mViewModel.liveDialogActivator.observe(viewLifecycleOwner) {
            if (it == true) {
                mViewModel.liveDialogActivator.value = false
                runGameEndDialog(mViewModel.showResultsAtTheEndOfGame)
            }
        }
    } // End of --- addObservers() ---

} /// -----
