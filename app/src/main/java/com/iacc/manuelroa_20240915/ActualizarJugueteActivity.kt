package com.iacc.manuelroa_20240915

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iacc.manuelroa_20240915.databinding.ActivityActualizarJugueteBinding

class ActualizarJugueteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityActualizarJugueteBinding
    private lateinit var db : JuguetesDataBaseHelper
    private var idJuguete : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarJugueteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = JuguetesDataBaseHelper(this)
        idJuguete = intent.getIntExtra("id_juguete", -1 )

        if(idJuguete == -1){
            finish()
            return
        }

        val juguete = db.getIdJuguete(idJuguete)
        binding.etJugueteNombreActualizar.setText(juguete.titulo)
        binding.etPrecioJugueteActualizar.setText(juguete.precio.toString())

        binding.ivActualizarJugueteCheck.setOnClickListener{
            val titulo = binding.etJugueteNombreActualizar.text.toString()
            val precio = binding.etPrecioJugueteActualizar.text.toString().toIntOrNull() ?: 0

            val juguete = Juguete(idJuguete , titulo, precio)

            db.updateJuguete(juguete)
            startActivity(Intent(this , MainActivity::class.java))
            finish()
            Toast.makeText(this, "Registro actualizado" , Toast.LENGTH_SHORT).show()
        }

    }
}