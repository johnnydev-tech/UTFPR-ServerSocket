package tech.johnnydev.clientsocket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.net.Socket

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            val ip = "10.0.2.2"
            val port = 12345
            val clientSocket = Socket(ip, port)
            val input = clientSocket.getInputStream().bufferedReader()
            val output = clientSocket.getOutputStream().bufferedWriter()

            while (true) {
                output.write("hora\n")
                output.flush()

                val response = input.readLine()
                println("Response: $response")
                Thread.sleep(1000)
            }

        }.start()
        return START_NOT_STICKY
    }
}