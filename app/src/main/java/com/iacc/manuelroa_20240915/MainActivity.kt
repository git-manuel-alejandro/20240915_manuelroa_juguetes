package com.iacc.manuelroa_20240915

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.iacc.manuelroa_20240915.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var db : JuguetesDataBaseHelper
    private lateinit var jugueteAdaptador: JugueteAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = JuguetesDataBaseHelper(this)
        jugueteAdaptador = JugueteAdaptador(db.getAllJuguetes() , this)

        binding.juguetesRv.layoutManager = LinearLayoutManager(this)
        binding.juguetesRv.adapter = jugueteAdaptador
        binding.FABAgregarEntrada.setOnClickListener{
            startActivity(Intent(applicationContext  , AgregarJugueteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        jugueteAdaptador.refreshList(db.getAllJuguetes())
    }

}