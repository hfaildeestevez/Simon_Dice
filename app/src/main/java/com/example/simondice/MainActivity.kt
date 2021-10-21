package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val empezarPartida: Button = findViewById(R.id.bJugar)
//Listener para botón play.
        //Llamo al Log para que me de informacion acerca de lo que esta pasando
        //Llamo a los metodos.
        empezarPartida.setOnClickListener{
            Log.i("estado" , "Boton play presionado")
            var contador=0
            mostrarRonda(contador)
            ejecutarSecuencia()
        }

    }
    //Método mostrar ronda
    private fun mostrarRonda(contador:Int){
    Log.i("ronda", "Ronda mostrada")

        val t:TextView=findViewById(R.id.contadorRonda)
        t.setText("Ronda: "+contador.toString())



    }
    //Metodo para que me de una secuencia a seguir
    private fun ejecutarSecuencia() {
    Log.i("secuencia","Secuencia Ejecutada")

    }

    private fun mensajeUsuario() {
    Log.i("mensaje","Mensaje mostrado")

    }

    private fun comprobarSecuencia() {
        Log.i("Secuencia","Secuencia comprobada")


    }

}
