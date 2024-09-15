package com.iacc.manuelroa_20240915

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iacc.manuelroa_20240915.databinding.ActivityAgregarJugueteBinding

class AgregarJugueteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAgregarJugueteBinding
    private lateinit var db : JuguetesDataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarJugueteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = JuguetesDataBaseHelper(this)

        binding.btnGuardarJuguete.setOnClickListener{
            val titulo = binding.etJuguete.text.toString()
            val precio = binding.etPrecio.text.toString().toIntOrNull() ?: 0


            val juguete = Juguete(0, titulo , precio)

            db.insertJuguete(juguete)
            startActivity(Intent(applicationContext , MainActivity::class.java))

            finishAffinity()

            Toast.makeText(applicationContext , "Juguete Guardado" , Toast.LENGTH_SHORT).show()

        }

    }
}