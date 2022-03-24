package com.david.sensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var brightness1: Sensor? = null
    private var brightness2: Sensor? = null
    private var brightness3: Sensor? = null
    private var brightness4: Sensor? = null
    private var brightness5: Sensor? = null

    private lateinit var text: TextView
    private lateinit var pb: TextView
    private lateinit var ac: TextView
    private  lateinit var ta: TextView
    private  lateinit var bu: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text1)
        pb = findViewById(R.id.text2)
        ac = findViewById(R.id.text3)
        ta = findViewById(R.id.text4)
        bu = findViewById(R.id.text5)

        setUpSensorStuff()
    }

    private fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        brightness1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        brightness2 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        brightness3 = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        brightness4 = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR)
        brightness5 = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]


            text.text = "Luz: $light1\n${brightness1(light1)}"
            pb.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val light2 = event.values[0]
            ac.text = "Aceleração: $light2\n${brightness2(light2)} "
            ac.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            val light2 = event.values[0]
            pb.text = "Aproximação: $light2\n${brightness3(light2)} "
            pb.text.toString()
        }
        if (event?.sensor?.type == Sensor.TYPE_GAME_ROTATION_VECTOR){
            val light2 = event.values[0]
            ta.text = "Rotação: $light2\n${brightness4(light2)} "
            ta.text.toString()
        }
        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD){
            val light2 = event.values[0]
            bu.text = "Bussola: $light2\n${brightness5(light2)} "
            bu.text.toString()
        }
    }

    private fun brightness1(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "muito escuro"
            in 1..10 -> "escuro"
            in 11..50 -> "quase normal"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "muito claro"
            else -> "gmais claro que isso é perioso"
        }
    }
    private  fun brightness2(brightness: Float):String{

        return  when (brightness.toInt()){
            0 -> "parado"
            in 0..1000-> "rapido"
            else -> "??"
        }
    }
    private  fun brightness3(brightness: Float):String{

        return when (brightness.toInt()){

            0->"perto"
            in 0..10->"Longe"
            else -> "???"
        }
    }
    private  fun brightness4(brightness: Float):String{

        return when (brightness.toInt()){

            0->"Rodando"
            in 0..10->"Rodando"
            else -> "???"
        }

    }
    private  fun brightness5(brightness: Float):String{

        return when (brightness.toInt()){

            0->"Norte"
            in 0..10->"Norte"
            else -> "Não é o norte"
        }

    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this,brightness1, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,brightness2, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,brightness3, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,brightness4, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,brightness5, SensorManager.SENSOR_DELAY_NORMAL)
    }


    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
