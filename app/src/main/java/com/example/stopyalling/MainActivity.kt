package com.example.stopyalling

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.stopyalling.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import android.media.ToneGenerator

import android.media.AudioManager
import android.graphics.Bitmap

import android.R.string.no
import android.os.Handler
import android.os.Message
import java.lang.Math.log10


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecorder:MediaRecorder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        mRecorder = MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile("/dev/null");
        mRecorder.prepare();
        mRecorder.start();


        Timer().schedule(timerTask {



            Timer().schedule(MyTaskClass(mRecorder,this@MainActivity), 100)

        }, 100)


    }

    fun getAmplitude():Int {
        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 50)

        if (this.mRecorder != null) {

            var value = mRecorder.maxAmplitude

            return (value)
        }
        else
            return 0;

    }




}