package org.example

import java.net.ServerSocket
import java.net.Socket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val port = 12345

    val serverSocket = ServerSocket(port)
    println("Server started on port $port")


    while (true) {


        val clientSocket = serverSocket.accept()
        println("Client connected: ${clientSocket.inetAddress.hostAddress}")



        Thread{
            handleClient(clientSocket)
        }.start()


    }

//    serverSocket.close()
//    println("Connection closed")

}

fun handleClient(clientSocket: Socket) {
try {
    val input = clientSocket.getInputStream().bufferedReader()
    val output = clientSocket.getOutputStream().bufferedWriter()
    println("Creating I/O streams")

    while (true) {

        val receivedMsg = input.readLine()
        println("Received message: $receivedMsg")

        val result = processOrder(receivedMsg)

        output.write("$result\n")
        output.flush() // FORCE MESSAGE TO BE SENT IGNORE BUFFER
        println("Sent: $result\n")


    }
}catch (e: Exception) {
    println("Error: ${e.message}")
    e.printStackTrace()
} finally {
    clientSocket.close()
}

}

fun processOrder(protocol: String): String {
    val now = LocalDateTime.now()

    var result = ""
    when (protocol) {
        "data" -> {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = now.format(formatter)
            result = "Current date: $formattedDate"
        }

        "hora" -> {
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val formattedDate = now.format(formatter)
            result = "Current time: $formattedDate"
        }

    }

    return result
}