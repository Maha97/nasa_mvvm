package com.maha.nasatest.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.maha.nasatest.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar()
    }
    private fun setActionBar(){
        val actionbar: ActionBar? = supportActionBar
        actionbar?.title = getString(R.string.rover_photos)
        actionbar?.elevation = 4.toFloat()
        actionbar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this,
                   androidx.appcompat.R.color.primary_dark_material_dark
                ))
        )
    }


}