package com.de.sh.gram.halligallicupsandroid

import android.app.Application
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketApplication: Application() {

        val socket: Socket = IO.socket("http://35.187.202.148:3000")
}