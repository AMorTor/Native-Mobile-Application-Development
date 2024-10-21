package com.example.cerraduramovil

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    private lateinit var numberEditText: EditText
    private lateinit var starButton: Button
    private lateinit var plusButton: Button
    private lateinit var bothButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberEditText = findViewById(R.id.numberEditText)
        starButton = findViewById(R.id.starButton)
        plusButton = findViewById(R.id.plusButton)
        bothButton = findViewById(R.id.bothButton)
        resultTextView = findViewById(R.id.resultTextView)

        starButton.setOnClickListener {
            val number = numberEditText.text.toString()

            // Usar corutina para realizar la llamada de red de forma asíncrona
            CoroutineScope(Dispatchers.IO).launch {
                val starResponse = fetchApiData("http://18.222.115.122:8080/api/closure/star/$number")

                // Actualizar la UI en el hilo principal
                withContext(Dispatchers.Main) {
                    val text = SpannableString("Cerradura de Kleene:\n $starResponse")
                    val boldSpan = StyleSpan(Typeface.BOLD)
                    val largeTextSpan = AbsoluteSizeSpan(24, true)
                    text.setSpan(boldSpan, 0, "Cerradura de Kleene:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    text.setSpan(largeTextSpan, 0, "Cerradura de Kleene:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    resultTextView.text = text
                }
            }
        }

        plusButton.setOnClickListener {
            val number = numberEditText.text.toString()

            // Usar corutina para realizar la llamada de red de forma asíncrona
            CoroutineScope(Dispatchers.IO).launch {
                val plusResponse = fetchApiData("http://18.222.115.122:8080/api/closure/plus/$number")

                // Actualizar la UI en el hilo principal
                withContext(Dispatchers.Main) {
                    val text = SpannableString("Cerradura de Positiva:\n $plusResponse")
                    val boldSpan = StyleSpan(Typeface.BOLD)
                    val largeTextSpan = AbsoluteSizeSpan(24, true)
                    text.setSpan(boldSpan, 0, "Cerradura de Positiva:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    text.setSpan(largeTextSpan, 0, "Cerradura de Positiva".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    resultTextView.text = text
                }
            }
        }

        bothButton.setOnClickListener {
            val number = numberEditText.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val starResponse = fetchApiData("http://18.222.115.122:8080/api/closure/star/$number")
                val plusResponse = fetchApiData("http://18.222.115.122:8080/api/closure/plus/$number")

                // Update the UI with both responses
                withContext(Dispatchers.Main) {
                    val textK = SpannableString("Cerradura de Kleene:\n $starResponse")
                    val boldSpanK = StyleSpan(Typeface.BOLD)
                    val largeTextSpanK = AbsoluteSizeSpan(24, true)
                    textK.setSpan(boldSpanK, 0, "Cerradura de Kleene:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    textK.setSpan(largeTextSpanK, 0, "Cerradura de Kleene:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                    val textP = SpannableString("Cerradura de Positiva:\n $plusResponse")
                    val boldSpanP = StyleSpan(Typeface.BOLD)
                    val largeTextSpanP = AbsoluteSizeSpan(24, true)
                    textP.setSpan(boldSpanP, 0, "Cerradura de Positiva:".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    textP.setSpan(largeTextSpanP, 0, "Cerradura de Positiva".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                    resultTextView.text = textK
                    resultTextView.append("\n\n")
                    resultTextView.append(textP)
                }
            }
        }


    }

    private suspend fun fetchApiData(urlString: String): String {
        return try {
            // Crear la URL
            val url = URL(urlString)

            // Abrir la conexión
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            // Leer la respuesta del servidor
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = reader.readText()

            // Cerrar el reader
            reader.close()

            // Devolver la respuesta leída
            response
        } catch (e: Exception) {
            // En caso de error, imprimir la excepción y devolver un mensaje de error
            e.printStackTrace()
            "Error: ${e.message}"
        }
    }
}