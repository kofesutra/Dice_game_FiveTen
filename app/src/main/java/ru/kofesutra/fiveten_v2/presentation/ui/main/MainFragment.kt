package ru.kofesutra.fiveten_v2.presentation.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import ru.kofesutra.fiveten_v2.R
import ru.kofesutra.fiveten_v2.databinding.FragmentMainBinding
import ru.kofesutra.fiveten_v2.presentation.MainActivity
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.WinWin
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.YouLoose
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.YouWin
import ru.kofesutra.fiveten_v2.presentation.utils.BottomSheet
import ru.kofesutra.fiveten_v2.presentation.utils.Variables

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mViewModel: MainViewModel by activityViewModels()

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
                        Toast.makeText(context, "Exit", Toast.LENGTH_LONG).show()
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

        Glide.with(this).load(R.drawable.dd1).override(180, 180).into(binding!!.dice1Draw)
        Glide.with(this).load(R.drawable.dd2).override(180, 180).into(binding!!.dice2Draw)
        Glide.with(this).load(R.drawable.dd3).override(180, 180).into(binding!!.dice3Draw)
        Glide.with(this).load(R.drawable.dd4).override(180, 180).into(binding!!.dice4Draw)
        Glide.with(this).load(R.drawable.dd5).override(180, 180).into(binding!!.dice5Draw)

        // Start the game
        binding?.playButton?.setOnClickListener {
            mViewModel.buttonCounts()
        }

    } // End of onViewCreated

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
                1 -> diceDrawTemp = R.drawable.dd1
                2 -> diceDrawTemp = R.drawable.dd2
                3 -> diceDrawTemp = R.drawable.dd3
                4 -> diceDrawTemp = R.drawable.dd4
                5 -> diceDrawTemp = R.drawable.dd5
                6 -> diceDrawTemp = R.drawable.dd6
            }
            when (i) {
                0 -> Glide.with(this).load(diceDrawTemp).override(180, 180)
                    .into(binding!!.dice1Draw)
                1 -> Glide.with(this).load(diceDrawTemp).override(180, 180)
                    .into(binding!!.dice2Draw)
                2 -> Glide.with(this).load(diceDrawTemp).override(180, 180)
                    .into(binding!!.dice3Draw)
                3 -> Glide.with(this).load(diceDrawTemp).override(180, 180)
                    .into(binding!!.dice4Draw)
                4 -> Glide.with(this).load(diceDrawTemp).override(180, 180)
                    .into(binding!!.dice5Draw)
            }
        }
    } // End of ----- Заполнение картинками -----

    private fun runGameEndDialog() {
        when (mViewModel.liveDialogSwitch) {
            1 -> {
                val dialogFragmentHere = YouWin()
                val manager = requireActivity().supportFragmentManager
                dialogFragmentHere.show(manager, "youwin")
                mViewModel.liveDialogSwitch = 0
                mViewModel.resetAllLiveDatas()
            }
            2 -> {
                val dialogFragmentHere = YouLoose()
                val manager = requireActivity().supportFragmentManager
                dialogFragmentHere.show(manager, "youloose")
                mViewModel.liveDialogSwitch = 0
                mViewModel.resetAllLiveDatas()
            }
            3 -> {
                val dialogFragmentHere = WinWin()
                val manager = requireActivity().supportFragmentManager
                dialogFragmentHere.show(manager, "winwin")
                mViewModel.liveDialogSwitch = 0
                mViewModel.resetAllLiveDatas()
            }
        }
    } // End f --- runGameEndDialog() ---

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
                runGameEndDialog()
            }
        }
    } // End of --- addObservers() ---

} /// -----
