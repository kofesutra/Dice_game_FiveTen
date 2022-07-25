package ru.kofesutra.fiveten_v2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.kofesutra.fiveten_v2.R
import ru.kofesutra.fiveten_v2.presentation.ui.dialogs.YouWin
import ru.kofesutra.fiveten_v2.presentation.utils.Singleton

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host)

        supportActionBar?.apply {
            title = "FiveTen_v2"
            setDisplayShowHomeEnabled(true)
            setLogo(R.drawable.dd5)
            setDisplayUseLogoEnabled(true)
        }
    } // End of onCreate

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} ///