package com.pnixg.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pnixg.R
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var counter: Int = 0

        clover1.setOnClickListener{
            counter++
        }

        clover2.setOnClickListener{
            counter++
        }

        clover3.setOnClickListener{
            counter++
        }

        val running : TextView = findViewById(R.id.running)


        val s : Long = "15".toLong() * 1000

        object : CountDownTimer( s , 1000) {

            override fun onTick(millisUntilFinished: Long) {
                running.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                MaterialAlertDialogBuilder(this@Game)
                    .setTitle("Time's up!")
                    .setMessage("You caught the clovers $counter times")
                    .setCancelable(false)
                    .setPositiveButton("Press to play again"){dialog, _ ->
                        startActivity(Intent(applicationContext, Game::class.java))
                        finish()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }.start()




        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val rand = Random()
                    val dx: Float = rand.nextFloat() * (ll_wasp.width-clover1.width)
                    val dy: Float = rand.nextFloat() * (ll_wasp.height-clover1.height)
                    clover1.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(500)
                        .start()
                }
            }
        }, 0, 300)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val rand = Random()
                    val dx: Float = rand.nextFloat() * (ll_wasp.width-clover1.width)
                    val dy: Float = rand.nextFloat() * (ll_wasp.height-clover1.height)
                    clover2.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(200)
                        .start()
                }
            }
        }, 0, 400)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val rand = Random()
                    val dx: Float = rand.nextFloat() * (ll_wasp.width-clover1.width)
                    val dy: Float = rand.nextFloat() * (ll_wasp.height-clover1.height)
                    clover3.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(700)
                        .start()
                }
            }
        }, 0, 600)


    }
}