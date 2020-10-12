package com.archeros.roadmap.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.archeros.roadmap.R
import com.archeros.roadmap.entity.Branch

class BranchAdapter(
    val branches: List<Branch>,
    val onClick: (Branch) -> Unit
): RecyclerView.Adapter<BranchAdapter.BranchesViewHolder>() {

    class BranchesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var card: CardView
        val nome: TextView
        val checked: ImageView

        init {
            card = view.findViewById(R.id.cardBranch)
            nome = view.findViewById(R.id.cardBranchNome)
            checked = view.findViewById(R.id.cardBranchChecked)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_branch, parent, false)
        return BranchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BranchesViewHolder, position: Int) {
        val context = holder.itemView.context
        val branch = branches[position]

        holder.nome.text = branch.name
        holder.itemView.setOnClickListener { onClick(branch) }
    }

    override fun getItemCount(): Int {
        return this.branches.size
    }
}