package com.de.sh.gram.halligallicupsandroid

import android.app.Application
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketApplication {
    val mSocket: Socket = IO.socket("http://10.156.145.143:8080")

    /*init {
        try {
            mSocket = IO.socket("http://10.156.145.143:8080")
        } catch (ue: URISyntaxException) {
            ue.printStackTrace()
        }
    }*/
    fun getSocket() : Socket = mSocket
}
