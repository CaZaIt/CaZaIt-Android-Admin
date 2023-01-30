package org.cazait.ui.view.state

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.cazait.R

class CafeStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_state)
    }
}