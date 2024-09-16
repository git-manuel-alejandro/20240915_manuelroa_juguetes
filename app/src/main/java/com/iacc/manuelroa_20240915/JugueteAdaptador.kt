package com.iacc.manuelroa_20240915

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class JugueteAdaptador (
    private var juguetes : List<Juguete>,
    context: Context) : RecyclerView.Adapter<JugueteAdaptador.JugueteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugueteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_juguete, parent, false)
        return JugueteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return juguetes.size
    }

    override fun onBindViewHolder(holder: JugueteViewHolder, position: Int) {
        val juguete = juguetes[position]
        holder.itemTitulo.text = juguete.titulo
        holder.itemPrecio.text = juguete.precio.toString()

        holder.ivActualizarJuguete.setOnClickListener{
            val intent = Intent(holder.itemView.context , ActualizarJugueteActivity::class.java).apply {
                putExtra("id_juguete" , juguete.id)

            }
            holder.itemView.context.startActivity(intent)

            Toast.makeText(
                holder.itemView.context, "el juguete seleccionado es ${juguete.id}", Toast.LENGTH_SHORT
            ).show()
        }

    }

    class JugueteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val itemTitulo : TextView = itemView.findViewById(R.id.tvTituloJuguete)
            val itemPrecio : TextView = itemView.findViewById(R.id.tvPrecioJuguete)
            val ivActualizarJuguete : ImageView = itemView.findViewById(R.id.ivActualizarJuguete)
    }

    fun refreshList(newJuguete : List<Juguete>){
        juguetes = newJuguete
        notifyDataSetChanged()
    }



}