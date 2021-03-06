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
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    //contador para mostrar las rondas
    private var contador:Int=0
    //array para guardar la secuencia
    var secuencia= arrayOf<Int>()
    //array para guardar la comprobacion de la secuencia
    var comprobarSecuencia=arrayOf<Int>()
    private var contadorSecuencia:Int=0
    var compo=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val empezarPartida: Button = findViewById(R.id.bJugar)
        //listener del boton jugar
        empezarPartida.setOnClickListener {
            Log.i("Estado","Boton Jugar pulsado")
            //aqui llamo al metodo mostrarRonda
            mostrarRonda()

            //creo la variable job para despues poder pausar la corrutina o pararla
            GlobalScope.launch(Dispatchers.Main) {
                //llamo al metodo ejecutar Secuencia para que me ejecute el juego
                empezarPartida.visibility=Button.INVISIBLE
                ejecutarSecuencia()
                //toast definida en mensajeUsuario
                mensajeUsuario(1)

            }
        }
        //listener de boton comprobar y de los colores
        val bcomprobar:Button=findViewById(R.id.bComprobar)
        //si los botones pulsados por el jugador son inferiores a los de la secuencia avisa de que
        // deberias volver a primaria para aprender a contar
        bcomprobar.setOnClickListener{
            if(secuencia.size==comprobarSecuencia.size){
                comprobarSecuencia()

            }else{
               finalizarPartida()
            }
        }
        val bVerde:Button=findViewById(R.id.bVerde)
        bVerde.setOnClickListener{
            comprobarSecuencia+=1
            Log.i("Estado","PULSADO verde")
        }
        val bRojo:Button=findViewById(R.id.bRojo)
        bRojo.setOnClickListener{
            comprobarSecuencia+=2
            Log.i("Estado","PULSADO rojo")
        }
        val bAmarillo:Button=findViewById(R.id.bAmarillo)
        bAmarillo.setOnClickListener{
            comprobarSecuencia+=3
            Log.i("Estado","PULSADO amarillo")
        }
        val bAzul:Button=findViewById(R.id.bAzul)
        bAzul.setOnClickListener{
            comprobarSecuencia+=4
            Log.i("Estado","PULSADO azul")
        }
    }

    private fun mostrarRonda(){
        contador++ //suma uno al contador
        Log.i("Estado","Mostrar Numero de Rondas")
        val t:TextView=findViewById(R.id.contadorRonda)
        t.setText("Ronda: "+contador.toString())
        t.visibility=TextView.VISIBLE
        //a??adimos un valor al array
        secuencia+=Random.nextInt(1, 4)
        Log.i("Estado","Aumento Array")
        Log.i("Estado","Fin metodo mostrar ronda")
    }
    suspend fun ejecutarSecuencia(){

        Log.i("Estado","Se ejecuta el juego")
        //Se ejecuta la secuencia
        //El color de los botones se cambia a blanco durante medio segundo para simular un parpadeo
        for (b in secuencia){
            when{
                b==1->{val bVerde: Button = findViewById(R.id.bVerde)
                    bVerde.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    delay(500L)
                    bVerde.setBackgroundColor(Color.parseColor("#179205"))
                    delay(500L)
                    Log.i("Estado","Verde")
                }
                b==2->{val bRojo: Button = findViewById(R.id.bRojo)
                    bRojo.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    delay(500L)
                    bRojo.setBackgroundColor(Color.parseColor("#C50505"))
                    delay(500L)
                    Log.i("Estado","Rojo")
                }
                b==3->{val bAmarillo: Button = findViewById(R.id.bAmarillo)
                    bAmarillo.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    delay(500L)
                    bAmarillo.setBackgroundColor(Color.parseColor("#FFFE00"))
                    delay(500L)
                    Log.i("Estado","Amarillo")
                }
                b==4->{val bAzul: Button = findViewById(R.id.bAzul)
                    bAzul.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    delay(500L)
                    bAzul.setBackgroundColor(Color.parseColor("#03FEED"))
                    delay(500L)
                    Log.i("Estado","Azul")
                }
            }

        }
    }
    //Aqui declaro las toast con los mensajes
    private fun mensajeUsuario(key:Int){
        Log.i("Estado","Mensaje por toast al usuario")
        val mensaje =when{

            key==1->"Te toca!"
            key==2->"Secuencia correcta!"
            key==4->"Te faltan pulsar botones, al menos aprende a contar!"
            else ->"Has pedido! Prueba otra vez!"
        }
        Toast.makeText(this.applicationContext, mensaje, Toast.LENGTH_SHORT).show()
    }
    //Metodo para que compruebe la secuencia y la aumente en su contador
    private fun comprobarSecuencia() {
        Log.i("Estado","Comprobar la secuencia del jugador")
        for(s in secuencia){
            if(s==comprobarSecuencia.get(contadorSecuencia)){
                Log.i("Estado",s.toString()+" "+comprobarSecuencia.get(contadorSecuencia))
                contadorSecuencia++

            }else{
                Log.i("Estado","No coincide")
                mensajeUsuario(4)
                compo=false
            }
        }

        if(compo){
            Log.i("Estado","siguiente ronda")
            mostrarRonda()
            mensajeUsuario(2)
            contadorSecuencia=0
            comprobarSecuencia=arrayOf()
            corrutina()
        }else{
            finalizarPartida()
        }
    }
        //metodo para finalizar la partida en el momento que el usuario fallo
    private fun finalizarPartida(){
        Log.i("Estado","Fin Partida")
        secuencia= arrayOf()
        contador=0
        compo=true
        val t:TextView=findViewById(R.id.contadorRonda)
        t.visibility=TextView.INVISIBLE
        val empezarPartida: Button = findViewById(R.id.bJugar)
        empezarPartida.visibility=Button.VISIBLE
        comprobarSecuencia=arrayOf()
        mensajeUsuario(9)

    }
    //corrutina que estoy reutilizando para que cada vez que acierte siga mostrando rondas
    fun corrutina(){
        GlobalScope.launch(Dispatchers.Main) {
            //llamo al metodo ejecutar Secuencia para que me ejecute el juego
            ejecutarSecuencia()
            mensajeUsuario(1)
        }
    }
}