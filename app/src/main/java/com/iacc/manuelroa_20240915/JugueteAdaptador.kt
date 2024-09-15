package com.iacc.manuelroa_20240915

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    }

    class JugueteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val itemTitulo : TextView = itemView.findViewById(R.id.tvTituloJuguete)
            val itemPrecio : TextView = itemView.findViewById(R.id.tvPrecioJuguete)
    }

    fun refreshList(newJuguete : List<Juguete>){
        juguetes = newJuguete
        notifyDataSetChanged()
    }



}