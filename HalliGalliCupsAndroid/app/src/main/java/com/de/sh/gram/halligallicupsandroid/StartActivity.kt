package com.de.sh.gram.halligallicupsandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_start.*
import org.json.JSONException
import org.json.JSONObject



class StartActivity : AppCompatActivity() {
    val socket: Socket = SocketApplication.socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        socket.connect()

        socket.on("start", start)

        btn_start_start.setOnClickListener {
            socket.emit("ready")
        }
    }

    val start: Emitter.Listener = Emitter.Listener { args->
        val data = args[0] as JSONObject
        val intent = Intent(applicationContext, MainActivity::class.java)
        try {
            val randNum: Int = data.getInt("randNum")
            intent.putExtra("randNum",randNum)
            startActivity(intent)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
