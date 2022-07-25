package ru.kofesutra.fiveten_v2.presentation.ui.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import ru.kofesutra.fiveten_v2.presentation.MainActivity
import ru.kofesutra.fiveten_v2.R
import ru.kofesutra.fiveten_v2.databinding.FragmentMainBinding
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.WinWin
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.YouLoose
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.YouWin
import ru.kofesutra.fiveten_v2.presentation.utils.BottomSheet
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    private val mediatorLiveData = MediatorLiveData<String>()
    private var observer: Observer<Any> = Observer { }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    } // End of onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Не отображать Back Button на ActionBar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

    // === Observers ===
        mediatorLiveData.addSource(mViewModel.liveMessage) {
            binding!!.message1.text = it
        }
        mediatorLiveData.addSource(mViewModel.liveButton) {
            binding!!.playButton.text = it
        }
        mediatorLiveData.addSource(Singleton.switchBottomSheet) {
            if (it == true) {
                runBottomSheet()
                Singleton.switchBottomSheet.value = false
            }
        }
        mediatorLiveData.addSource(Singleton.liveMyResultsNow) {
            binding!!.myScoresNow.text = it.toString()
        }
        mediatorLiveData.addSource(Singleton.liveAndrResultNow) {
            binding!!.andrScoresNow.text = it.toString()
        }
        mediatorLiveData.addSource(Singleton.liveMyResultTotal) {
            binding!!.myScoresTotal.text = it.toString()
        }
        mediatorLiveData.addSource(Singleton.liveAndrResultTotal) {
            binding!!.andrScoresTotal.text = it.toString()
        }
        mediatorLiveData.addSource(Singleton.liveDicesImages) {
            bindDicesImages()
        }
        mediatorLiveData.addSource(Singleton.liveDialogActivator) {
            if (it == true) {
                Singleton.liveDialogActivator.value = false
                runDialog()
                }
        }
        mediatorLiveData.observe(viewLifecycleOwner, observer)
    // === End of Observers ===

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

        binding?.playButton?.setOnClickListener {
            mViewModel.buttonCounts()
        }


        Log.d(TAG, "222 ${Singleton.liveDialogSwitch}")


    } // End of onViewCreated

    override fun onStart() {
        super.onStart()
        mediatorLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun onStop() {
        super.onStop()
        mediatorLiveData.removeObserver(observer)
    }

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
    private fun bindDicesImages(){
        // ----- Заполнение картинками -----
        var diceDrawTemp = 0
        for (i in 0..4) {
            when (Singleton.valuesListDraw[i]) {
                1 -> diceDrawTemp = R.drawable.dd1
                2 -> diceDrawTemp = R.drawable.dd2
                3 -> diceDrawTemp = R.drawable.dd3
                4 -> diceDrawTemp = R.drawable.dd4
                5 -> diceDrawTemp = R.drawable.dd5
                6 -> diceDrawTemp = R.drawable.dd6
            }
            when (i) {
                0 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding!!.dice1Draw)
                1 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding!!.dice2Draw)
                2 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding!!.dice3Draw)
                3 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding!!.dice4Draw)
                4 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding!!.dice5Draw)
            }
        }
    } // End of ----- Заполнение картинками -----

    private fun runDialog() {
        when(Singleton.liveDialogSwitch){
            1 -> {
                val dialogFragmentHere = YouWin()
                val manager = requireActivity().supportFragmentManager
                dialogFragmentHere.show(manager, "youwin")
                Singleton.liveDialogSwitch = 0
            }
            2 -> {
                val dialogFragmentHere = YouLoose()
                val manager = requireActivity().supportFragmentManager
                dialogFragmentHere.show(manager, "youloose")
                Singleton.liveDialogSwitch = 0
            }
            3 -> {
                val dialogFragmentHere = WinWin()
                val manager = requireActivity().supportFragmentManager
                dialogFragmentHere.show(manager, "winwin")
                Singleton.liveDialogSwitch = 0
            }
        }
    }

} ///
