package com.de.sh.gram.halligallicupsandroid

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val socket = SocketApplication.getSocket()
        socket.connect()
        Log.d("Debug", socket.connected().toString())

        socket.on("start",start)


        btn_start_start.setOnClickListener {
            socket.emit("ready")
        }
    }

    var start: Emitter.Listener = Emitter.Listener {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
