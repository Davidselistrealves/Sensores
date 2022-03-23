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
    private var brightness: Sensor? = null
    private lateinit var text: TextView
    private lateinit var pb: TextView
    private lateinit var ac: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        text = findViewById(R.id.text1)
        pb = findViewById(R.id.text2)
        ac = findViewById(R.id.text3)

        setUpSensorStuff()
    }

    private fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        val device = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]

            text.text = "Sensor: $light1\n${brightness(light1)}"
            pb.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){

            ac.text.toString()
        }
    }

    private fun brightness(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "muito escuro"
            in 1..10 -> "escuro"
            in 11..50 -> "quase normal"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "muito claro"
            else -> "gmais claro que isso é perioso"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
    }


    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
