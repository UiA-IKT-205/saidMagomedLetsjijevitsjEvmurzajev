
package no.uia.ikt205.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import com.google.android.material.slider.RangeSlider
import no.uia.ikt205.pomodoro.util.millisecondsToDescriptiveTime

class MainActivity : AppCompatActivity() {

    lateinit var timer:CountDownTimer
    lateinit var startButton:Button
    lateinit var buttonstop:Button
    lateinit var coutdownDisplay:TextView
    lateinit var pauseTimer:CountDownTimer
    lateinit var timeSelectSlider: SeekBar
    lateinit var repetitionInput: EditText
    lateinit var pauseTimeSelect:SeekBar




    var timeToCountDownInMs = 15 * 60000L
    val timeTicks = 1000L
    private var isTimeStarted = false
    private var hasPauseTimerStarted: Boolean = false
    private var pauseTime = 15 * 60000L
    private var timerRepetitionAmount:Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeSelectSlider = findViewById<SeekBar>(R.id.timeSelectSlider)
        timeSelectSlider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (isTimeStarted) {
                    timer.cancel()
                    isTimeStarted = false
                }
                timeToCountDownInMs = progress * 60000L
                updateCountDownDisplay(timeToCountDownInMs)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("Not implemented")
            } override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("Not implemented")
            }
        })

        pauseTimeSelect = findViewById<SeekBar>(R.id.pauseTimeSelect)
        pauseTimeSelect.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pauseTime = progress * 60000L
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("Not implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("Not implemented")
            }
        })

        repetitionInput = findViewById<EditText>(R.id.repetitionAmount)
        startButton = findViewById<Button>(R.id.startCountdownButton)
        startButton.setOnClickListener(){
            if (isTimeStarted){
                Toast.makeText(this@MainActivity, "Timer is running", Toast.LENGTH_SHORT).show()
            }else {
                startCountDown(it)
                isTimeStarted = true;
            }
        }


        coutdownDisplay = findViewById<TextView>(R.id.countDownView)
        updateCountDownDisplay(timeToCountDownInMs)
    }

    fun startCountDown(v: View){

        if (isTimeStarted) {
            Toast.makeText(this@MainActivity, "Timer is already running", Toast.LENGTH_SHORT).show()
            timer.cancel()
            isTimeStarted = false
            Toast.makeText(this@MainActivity, "Stopping all timers", Toast.LENGTH_SHORT).show()
            return
        }
        timer = object : CountDownTimer(timeToCountDownInMs,timeTicks) {
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Time is up!", Toast.LENGTH_SHORT).show()
                updateCountDownDisplay(timeToCountDownInMs)
                isTimeStarted = false

                timerRepetitionAmount = repetitionInput.text.toString().toInt()
                if (timerRepetitionAmount > 0) {
                    Toast.makeText(this@MainActivity, "Tid for en pause", Toast.LENGTH_SHORT).show()
                    startPauseCountDown(v)
                    timerRepetitionAmount--
                    repetitionInput.setText(timerRepetitionAmount.toString())
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                updateCountDownDisplay(millisUntilFinished)
                timeToCountDownInMs = millisUntilFinished
            }
        }

        timer.start()
        isTimeStarted = true

    }

    fun updateCountDownDisplay(timeInMs:Long){
        coutdownDisplay.text = millisecondsToDescriptiveTime(timeInMs)
    }

    fun startPauseCountDown(v: View){
        if (hasPauseTimerStarted) {
            pauseTimer.cancel()
            hasPauseTimerStarted = false
        }
        pauseTimer = object : CountDownTimer(pauseTime, timeTicks) {
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Pausen er over", Toast.LENGTH_SHORT).show()
                hasPauseTimerStarted = false

                timerRepetitionAmount = repetitionInput.text.toString().toInt()
                if (timerRepetitionAmount > 0) {
                    timer.start()
                } else {
                    timer.cancel()
                }

            }

            override fun onTick(millisUntilFinished: Long) {
                updateCountDownDisplay(millisUntilFinished)
            }
        }
        pauseTimer.start()
        hasPauseTimerStarted = true
    }


}







