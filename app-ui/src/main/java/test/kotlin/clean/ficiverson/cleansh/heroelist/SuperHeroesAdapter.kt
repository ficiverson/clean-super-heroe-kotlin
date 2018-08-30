package test.kotlin.clean.ficiverson.cleansh.heroelist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_super_heroe.view.*
import test.kotlin.clean.ficiverson.model.SuperHeroe
import kotlin.clean.ficiverson.cleansh.R

class SuperHeroesAdapter(
    private val context: Context,
    private val superHeroes: List<SuperHeroe>,
    private val listener: (SuperHeroe) -> Unit
) : RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroesViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder =
        SuperHeroesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_super_heroe, parent, false)).apply {
            itemView.setOnClickListener {
                listener(superHeroes[adapterPosition])
            }
        }

    override fun getItemCount() = superHeroes.size

    override fun getItemId(position: Int) = superHeroes[position].hashCode().toLong()

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        holder.bind(superHeroes[position])
    }

    class SuperHeroesViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(superHeroe: SuperHeroe) {
            item.itemSuperHeroeName.text = superHeroe.name
        }
    }
}