package tech.johnnydev.clientsocket

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.net.Socket

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val isAirplaneMode = intent.getBooleanExtra("state", false)

        if(isAirplaneMode) {
            Thread {
                val ip = "10.0.2.2"
                val port = 12345
                val clientSocket = Socket(ip, port)
                val input = clientSocket.getInputStream().bufferedReader()
                val output = clientSocket.getOutputStream().bufferedWriter()


                output.write("hora\n")
                output.flush()

                val response = input.readLine()
                println("AIRPLANE: $response")


            }.start()
        }
    }
}