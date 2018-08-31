package test.kotlin.clean.ficiverson.cleansh.heroelist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import kotlinx.android.synthetic.main.item_super_heroe.view.*
import test.kotlin.clean.ficiverson.cleansh.extensions.setImageUrl
import test.kotlin.clean.ficiverson.presentation.model.SuperHeroeView
import kotlin.clean.ficiverson.cleansh.R

class SuperHeroesAdapter(
    private val context: Context,
    private val listener: (SuperHeroeView) -> Unit
) : RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroesViewHolder>(), Filterable {

    private var superHeroes = emptyList<SuperHeroeView>()
    private var superHeroesFiltered = mutableListOf<SuperHeroeView>()

    init {
        setHasStableIds(true)
    }

    fun setSuperHeroes(superHeroes: List<SuperHeroeView>) {
        this.superHeroes = superHeroes
        superHeroesFiltered.clear()
        superHeroesFiltered.addAll(superHeroes)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder =
        SuperHeroesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_super_heroe, parent, false)).apply {
            itemView.setOnClickListener {
                listener(superHeroesFiltered[adapterPosition])
            }
        }

    override fun getItemCount() = superHeroesFiltered.size

    override fun getItemId(position: Int) = superHeroesFiltered[position].hashCode().toLong()

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        holder.bind(superHeroesFiltered[position])
    }

    @Suppress("unchecked_cast")
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val result = FilterResults()

                if (constraint?.isNotEmpty() == true) {
                    val constraintLowerCase = constraint.toString().toLowerCase()

                    val filteredSuperHeroes = superHeroes.filter {
                        it.name.toLowerCase().startsWith(constraintLowerCase)
                    }

                    result.values = filteredSuperHeroes
                    result.count = filteredSuperHeroes.size
                } else {
                    synchronized(this) {
                        result.values = superHeroes
                        result.count = superHeroes.size
                    }
                }
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                superHeroesFiltered = results.values as MutableList<SuperHeroeView>
                notifyDataSetChanged()
            }
        }
    }

    class SuperHeroesViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(superHeroe: SuperHeroeView) {
            with(superHeroe) {
                item.itemSuperHeroeName.text = name
                item.itemSuperHeroeImage.setImageUrl(avatar)
            }
        }
    }
}