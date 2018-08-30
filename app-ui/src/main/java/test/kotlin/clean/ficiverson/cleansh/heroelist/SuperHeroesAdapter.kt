package test.kotlin.clean.ficiverson.cleansh.heroelist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_super_heroe.view.*
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import kotlin.clean.ficiverson.cleansh.R

class SuperHeroesAdapter(
    private val context: Context,
    private val listener: (SuperHeroeView) -> Unit
) : RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroesViewHolder>() {

    private val superHeroes = mutableListOf<SuperHeroeView>()

    init {
        setHasStableIds(true)
    }

    fun setSuperHeroes(superHeroes: List<SuperHeroeView>) {
        this.superHeroes.run {
            clear()
            addAll(superHeroes)
        }
        notifyDataSetChanged()
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
        fun bind(superHeroe: SuperHeroeView) {
            item.itemSuperHeroeName.text = superHeroe.name
        }
    }
}