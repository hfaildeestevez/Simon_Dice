package com.example.simondice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var contador=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val empezarPartida: Button = findViewById(R.id.bJugar)
        //Listener para botón play.
        // Llamo al Log para que me de informacion acerca de lo que esta pasando
        //Llamo a los metodos.
        empezarPartida.setOnClickListener{

            //Creo el texto que quiero que se muestre en la toast y el tamaño.
            val text = "GAME ON!"
            val duration = Toast.LENGTH_SHORT
            //llamo a la toast para que se muestre.
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            Log.i("estado" , "Boton play presionado")

            //Creo contador para que vaya acumulando las rondas

            mostrarRonda()

            val job= GlobalScope.launch (Dispatchers.Main){
                secuenciaBotones()
            }

        }

    }
    //Método mostrar ronda
    private fun mostrarRonda(){
        Log.i("ronda", "Ronda mostrada")
        //Añado el contenido del contador a un textView
        val t:TextView=findViewById(R.id.contadorRonda)
        contador++

        t.setText("Ronda: "+contador.toString())
    }
    //Metodo para que me de una secuencia a seguir
    suspend fun ejecutarSecuencia() {
        Log.i("Secuencia","Secuencia Ejecutada")




        delay(3000L)
        Log.i("estado", "Con delay")



    }

    private fun mensajeUsuario() {
        Log.i("mensaje","Mensaje mostrado")

    }

    private fun comprobarSecuencia() {
        Log.i("Secuencia","Secuencia comprobada")


    }
    suspend fun secuenciaBotones() {

        // val secuencia = ronda +1
        val Colores = arrayOf("#008000","#FFFF00","#3498DB","#EC4849")
        var arrayBotones = hashMapOf<Int,Button>()
        arrayBotones[0]= findViewById(R.id.bVerde)
        arrayBotones[1]= findViewById(R.id.bAmarillo)
        arrayBotones[2] = findViewById(R.id.bAzul)
        arrayBotones[3] = findViewById(R.id.bRojo)

        var secuencia:Array<Int> = arrayOf()
        var random = (0..3).random()
        secuencia = arrayOf(random)
        for(i in 1..contador) {
            delay(500L)
            arrayBotones[contador]?.setBackgroundColor(Color.WHITE)
            delay(500L)
            arrayBotones[contador]?.setBackgroundColor(Color.parseColor(Colores[contador]))
            //var todosColores = arrayListOf(cuatroColores[random])
            //val b: Button = arrayBotones[random]!!
            //delay(1000L)
            //arrayBotones[ramdon]?.setBackgroundColor()
            //b.foregroundTintList = ColorStateList.valueOf(Color.WHITE)
        }
        //return secuencia
    }


}
