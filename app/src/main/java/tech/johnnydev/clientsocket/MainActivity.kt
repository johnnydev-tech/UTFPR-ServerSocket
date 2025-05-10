package tech.johnnydev.clientsocket

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.net.Socket
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var rbHora: RadioButton
    private lateinit var rbData: RadioButton
    private lateinit var tvResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rbHora = findViewById(R.id.rbHora)
        rbData = findViewById(R.id.rbData)

        tvResultado = findViewById(R.id.tvResposta)


    }

    override fun onStart() {
        super.onStart()
//        val serviceIntent = Intent(this, MyService::class.java)
//        startService(serviceIntent)
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(MyReceiver(), it)
        }
    }


}