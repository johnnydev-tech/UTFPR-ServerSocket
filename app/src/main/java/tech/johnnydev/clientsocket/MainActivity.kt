package tech.johnnydev.clientsocket

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.net.Socket

class MainActivity : AppCompatActivity() {

    private lateinit var rnHora: RadioButton
    private lateinit var rnData: RadioButton
    private lateinit var btEnviar: Button
    private lateinit var tvResultado: TextView
    private  lateinit var progressBar: ProgressBar

    private val ip = "10.0.2.2" // EMULATOR
    private val port = 12345

    private lateinit var clientSocket: Socket
    private lateinit var input: BufferedReader
    private lateinit var output: BufferedWriter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rnHora = findViewById(R.id.rbHora)
        rnData = findViewById(R.id.rbData)
        btEnviar = findViewById(R.id.btEnviar)
        tvResultado = findViewById(R.id.tvResposta)
        progressBar = findViewById(R.id.progress_bar)


        btEnviar.setOnClickListener {
            btEnviarOnClick()
        }
    }

    private fun btEnviarOnClick() {
        progressBar.visibility = ProgressBar.VISIBLE

        Thread {
            if (!::clientSocket.isInitialized) {
                clientSocket = Socket(ip, port)

                input = clientSocket.getInputStream().bufferedReader()
                output = clientSocket.getOutputStream().bufferedWriter()
            }

            when (rnHora.isChecked) {
                true -> output.write("hora\n")
                false -> output.write("data\n")
            }

            output.flush()
            val response = input.readLine()
            runOnUiThread {
                Thread.sleep(2000)
                tvResultado.text = response
                progressBar.visibility = ProgressBar.GONE
            }


        }.start()
    }

    override fun onStop() {
        super.onStop()
        clientSocket.close()
    }
}