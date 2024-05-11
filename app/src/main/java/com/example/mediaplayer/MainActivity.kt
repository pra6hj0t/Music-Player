package com.example.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer : MediaPlayer
    var totalTime : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val play  = findViewById<ImageView>(R.id.play)
        val pause = findViewById<ImageView>(R.id.pause)
//        val btnStop = findViewById<ImageView>(R.id.stop)
        val btnSeekBar = findViewById<SeekBar>(R.id.seekBar)

        mediaPlayer = MediaPlayer.create(this ,R.raw.music)
        mediaPlayer.setVolume(1f,1f)
        totalTime = mediaPlayer.duration



        play.setOnClickListener {
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
//        btnStop.setOnClickListener {
//            mediaPlayer.stop()
//            mediaPlayer.reset()
//            mediaPlayer.release()
//        }

        btnSeekBar.max = totalTime

        btnSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               if(fromUser){
                   mediaPlayer.seekTo(progress)
               }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                try {
                    btnSeekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)
                }catch (exception : java.lang.Exception){
                    btnSeekBar.progress = 0
                }

            }

        },0)

    }
}