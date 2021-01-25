package no.uia.ikt205.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import no.uia.ikt205.pomodoro.util.millisecondsToDescriptiveTime

class MainActivity : AppCompatActivity() {

    lateinit var timer:CountDownTimer
    lateinit var startButton:Button
    lateinit var buttonstop:Button
    lateinit var coutdownDisplay:TextView
    lateinit var timebutton30:Button
    lateinit var timebutton60:Button
    lateinit var timebutton90:Button
    lateinit var timebutton120:Button



    var timeToCountDownInMs = 5000L
    val timeTicks = 1000L
    var isTimeStarted = false
    var stoptime = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timebutton30 = findViewById<Button>(R.id.Button30)
        timebutton60 = findViewById<Button>(R.id.Button60)
        timebutton90 = findViewById<Button>(R.id.Button90)
        timebutton120 = findViewById<Button>(R.id.Button120)

        timebutton30.setOnClickListener() {
            if (!isTimeStarted) {
                timeToCountDownInMs = 30 * 60 * 1000;
                updateCountDownDisplay(timeToCountDownInMs)
            }
        }
        timebutton60.setOnClickListener(){
            if (!isTimeStarted){
                timeToCountDownInMs = 60 * 60 * 1000;
                updateCountDownDisplay(timeToCountDownInMs)
            }

        }
        timebutton90.setOnClickListener(){
            if (!isTimeStarted){
                timeToCountDownInMs = 90 * 60 * 1000;
                updateCountDownDisplay(timeToCountDownInMs)
            }

        }
        timebutton120.setOnClickListener(){
            if (!isTimeStarted){
                timeToCountDownInMs = 120 * 60 * 1000;
                updateCountDownDisplay(timeToCountDownInMs)
            }

        }


        startButton = findViewById<Button>(R.id.startCountdownButton)
       startButton.setOnClickListener(){
           startCountDown(it)
           if (isTimeStarted){
               Toast.makeText(this@MainActivity, "Timer is running", Toast.LENGTH_SHORT).show()

           }else {
               startCountDown(it)

               isTimeStarted = true;
           }
       }

       coutdownDisplay = findViewById<TextView>(R.id.countDownView)
        buttonstop = findViewById<Button>(R.id.stopCountdownButton)
        buttonstop.setOnClickListener(){
            if (isTimeStarted){
                timer.cancel()
                timeToCountDownInMs = stoptime;
                updateCountDownDisplay(timeToCountDownInMs)
                isTimeStarted = false;

            }else {
                Toast.makeText(this@MainActivity, "Timer is not started!", Toast.LENGTH_SHORT).show()
            }
        }
        coutdownDisplay = findViewById<TextView>(R.id.countDownView)
    }


    fun startCountDown(v: View){

        timer = object : CountDownTimer(timeToCountDownInMs,timeTicks) {
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Arbeidsøkt er ferdig", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity,"Time is up!", Toast.LENGTH_SHORT).show()
                updateCountDownDisplay(timeToCountDownInMs)
                isTimeStarted = false
            }


            override fun onTick(millisUntilFinished: Long) {

                    updateCountDownDisplay(millisUntilFinished)
                    timeToCountDownInMs = millisUntilFinished

            }
        }

        timer.start()
    }

    fun updateCountDownDisplay(timeInMs:Long){
        coutdownDisplay.text = millisecondsToDescriptiveTime(timeInMs)
    }

}