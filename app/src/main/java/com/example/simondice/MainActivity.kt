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
    var listrandom:Array<Int> = arrayOf()
    var secuencia: MutableList<Int> = listrandom.toMutableList()
    var secuenciaComprobar=arrayOf<Int>()
    val Colores = arrayOf("#008000","#FFFF00","#3498DB","#EC4849")
    var arrayBotones = hashMapOf<Int,Button>()
    private var contadorSecuencia:Int=0
    var compo=true




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val empezarPartida: Button = findViewById(R.id.bJugar)
        //Listener para botón play.
        // Llamo al Log para que me de informacion acerca de lo que esta pasando
        //Llamo a los metodos.
        empezarPartida.setOnClickListener{

            val bcomprobar:Button=findViewById(R.id.bComprobar)
            bcomprobar.setOnClickListener{
                comprobarSecuencia()
            }
            val bVerde:Button=findViewById(R.id.bVerde)
            bVerde.setOnClickListener{
                secuenciaComprobar+=1
                Log.i("Estado","PULSADO verde")
            }
            val bRojo:Button=findViewById(R.id.bRojo)
            bRojo.setOnClickListener{
                secuenciaComprobar+=2
                Log.i("Estado","PULSADO rojo")
            }
            val bAmarillo:Button=findViewById(R.id.bAmarillo)
            bAmarillo.setOnClickListener{
                secuenciaComprobar+=3
                Log.i("Estado","PULSADO amarillo")
            }
            val bAzul:Button=findViewById(R.id.bAzul)
            bAzul.setOnClickListener{
                secuenciaComprobar+=4
                Log.i("Estado","PULSADO azul")
            }

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

    suspend fun secuenciaBotones() {

        // val secuencia = ronda +1
        arrayBotones[0]= findViewById(R.id.bVerde)
        arrayBotones[1]= findViewById(R.id.bAmarillo)
        arrayBotones[2] = findViewById(R.id.bAzul)
        arrayBotones[3] = findViewById(R.id.bRojo)


        var random = (0..3).random()
        secuencia.add(random)
        val tamano = contador -1
        for(i in 0..tamano) {
            delay(500L)
            arrayBotones[secuencia[i]]?.setBackgroundColor(Color.WHITE)
            delay(500L)
            arrayBotones[secuencia[i]]?.setBackgroundColor(Color.parseColor(Colores[secuencia[i]]))


        }

    }
    private fun comprobarSecuencia() {
        Log.i("Estado","Comprobar la secuencia del jugador")
        for(i in secuencia){
            if(i==secuenciaComprobar.get(contadorSecuencia)){
                Log.i("Estado",i.toString()+" "+secuenciaComprobar.get(contadorSecuencia))
                contadorSecuencia++

            }else{
                Log.i("Estado","No coincide")
                //mensajeUsuario(4)
                secuenciaComprobar=arrayOf()
                compo=false
            }
        }

        if(compo){
            Log.i("Estado","siguiente ronda")
            mostrarRonda()
          //  mensajeUsuario(3)

            secuenciaComprobar=arrayOf()
            corrutina()
        }else{
            finalizarPartida()
        }
    }

    private fun corrutina() {

        GlobalScope.launch(Dispatchers.Main) {
            //llamo al metodo ejecutar Secuencia para que me ejecute el juego
            ejecutarSecuencia()
            //mensajeUsuario(2)
        }
    }

    private fun finalizarPartida(){
        Log.i("Estado","Fin Partida")
        secuencia = listrandom.toMutableList()
        contador=0
        compo=true

        val t:TextView=findViewById(R.id.contadorRonda)
        t.visibility=TextView.INVISIBLE
        val empezarPartida: Button = findViewById(R.id.bAzul)
        empezarPartida.visibility=Button.VISIBLE


        GlobalScope.launch(Dispatchers.Main) {
            //llamo al metodo ejecutar Secuencia para que me ejecute el juego
            ejecutarSecuencia()
           // mensajeUsuario(2)
        }
    }


            }






