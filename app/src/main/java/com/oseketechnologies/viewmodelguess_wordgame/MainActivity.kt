package com.oseketechnologies.viewmodelguess_wordgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oseketechnologies.viewmodelguess_wordgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}