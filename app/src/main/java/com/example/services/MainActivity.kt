package com.example.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)

        Log.d("TIMER", "Current thread" + Thread.currentThread().name)
//A background thread to run a 5 second timer


//Create/Mimic a timer behaviour in the background to represent a long running task
        val runnable = Runnable {

            var num: Int = 1
            Log.d("TIMER", "Current thread" + Thread.currentThread().name)
            while (num < 7) {
//Use this as a timer

                try {
                    if (num == 6) {

                        num = 8

                    }
                    Log.d("TIMER", "Second $num")
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                num++
            }
        }
//Pass the runnable above to a Thread constructor to be able to start the run method
        val thread = Thread(runnable)
        thread.start()

        btnStart.setOnClickListener {
            val intentService = Intent(applicationContext, AudioPlayerService::class.java)
            startService(intentService)
        }
        btnStop.setOnClickListener {
            val intentService = Intent(applicationContext, AudioPlayerService::class.java)
            stopService(intentService)

        }
    }
}
