package com.saldivar.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.saldivar.core.loadByResourceWithoutCache
import com.saldivar.home.databinding.LsvItemDishBinding
import java.util.*

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/

class RecipeListViewHolder(
    private val binding: LsvItemDishBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: RecipeModelUI) {
        with(binding) {
            title.text = data.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            data.image?.let { image.loadByResourceWithoutCache(it) }
        }
    }
}