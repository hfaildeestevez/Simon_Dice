package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val empezarPartida: Button = findViewById(R.id.bJugar)

        empezarPartida.setOnClickListener{

            startGame()

        }



    }

    private fun startGame(){



    }


}
