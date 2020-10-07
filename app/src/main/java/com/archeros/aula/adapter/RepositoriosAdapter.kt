package com.archeros.aula.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.archeros.aula.R
import com.archeros.aula.entity.Repositorio
import com.squareup.picasso.Picasso

class RepositoriosAdapter (
    val repositorios: List<Repositorio>,
    val onClick: (Repositorio) -> Unit
): RecyclerView.Adapter<RepositoriosAdapter.RepositoriosViewHolder>() {

    class RepositoriosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nome: TextView
        val image : ImageView
        var loader: ProgressBar
        var card: CardView

        init {
            nome = view.findViewById<TextView>(R.id.cardTvNome)
            image = view.findViewById<ImageView>(R.id.cardIvImagem)
            loader = view.findViewById<ProgressBar>(R.id.cardPgLoader)
            card = view.findViewById<CardView>(R.id.cardRepositorio)
        }
    }

    // inflar layout do adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriosViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_repositorio, parent, false)
        return RepositoriosViewHolder(view)
    }

    // bind para atualizar Views com os dados
    override fun onBindViewHolder(holder: RepositoriosViewHolder, position: Int) {
        val context = holder.itemView.context
        val repositorio = repositorios[position]

        // atualizar dados de disciplina
        holder.nome.text = repositorio.nome
        holder.loader.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(repositorio.foto).fit().into(holder.image,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.loader.visibility = View.GONE
                }

                override fun onError() {
                    holder.loader.visibility = View.GONE
                }
            })

        holder.itemView.setOnClickListener { onClick(repositorio) }
    }

    override fun getItemCount(): Int {
        return this.repositorios.size
    }
}