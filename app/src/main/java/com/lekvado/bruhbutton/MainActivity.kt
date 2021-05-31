package com.lekvado.bruhbutton

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    var soundPool: SoundPool? = null
    private var sound = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the SoundPool API
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build()
        } else {
            SoundPool(
                    8,
                    AudioManager.STREAM_MUSIC,
                    0)
        }
        sound = soundPool!!.load(
                this,
                R.raw.sound,
                1)

        // Set the onClick events
        var button: Button = findViewById(R.id.button)
        var layout: ConstraintLayout = findViewById(R.id.layout)
        var onClickListener: View.OnClickListener = View.OnClickListener { soundPool!!.play(sound, 1f, 1f, 0, 0, 1f) }
        button.setOnClickListener(onClickListener)
        layout.setOnClickListener(onClickListener)
    }
}