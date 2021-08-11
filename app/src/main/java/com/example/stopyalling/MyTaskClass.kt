package com.example.stopyalling

import android.media.AudioManager
import android.media.MediaRecorder
import android.media.ToneGenerator
import android.widget.TextView
import kotlinx.coroutines.delay
import java.time.Instant
import java.util.*





class MyTaskClass(mRecorder: MediaRecorder,mainActivity:MainActivity) : TimerTask() {
    var my_recorder:MediaRecorder
    var mainActivity:MainActivity
    init {
        this.my_recorder = mRecorder
        this.mainActivity=mainActivity
    }
    override fun run() {

        var powerDb = 20 * Math.log10(getAmplitude().toDouble());
        this.mainActivity.runOnUiThread(MyRunable(powerDb,this.mainActivity))
        var startTime=Instant.now().epochSecond
        if (powerDb>70) {
            this.my_recorder.reset()

            while (Instant.now().epochSecond - startTime < 10) {

                val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                toneGen1.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI, 50)
                Thread.sleep(50)


            }
            startTime = Instant.now().epochSecond

            while (Instant.now().epochSecond - startTime < 3) {

            }
            this.my_recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            this.my_recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            this.my_recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            this.my_recorder.setOutputFile("/dev/null");
            this.my_recorder.prepare();
            this.my_recorder.start();


        }

        Timer().schedule(MyTaskClass(this.my_recorder,this.mainActivity), 100)

    }
    fun getAmplitude():Int {


        if (this.my_recorder != null) {

            var value = my_recorder.maxAmplitude

            return (value)
        }
        else
            return 0;

    }
}

class MyRunable(decybelVolume:Double,mainActivity:MainActivity):Runnable {
    var decybelVolume:Double
    var mainActivity:MainActivity
    init {
        this.decybelVolume=decybelVolume
        this.mainActivity=mainActivity
    }
    override fun run() {
        var lab_veiw=this.mainActivity.findViewById<TextView>(R.id.labDecybelValue)
        lab_veiw.setText("$decybelVolume")
    }
}