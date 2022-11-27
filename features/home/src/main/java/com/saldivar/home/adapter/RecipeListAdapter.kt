package com.saldivar.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.saldivar.core.convertJson
import com.saldivar.home.databinding.LsvItemDishBinding

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class RecipeListAdapter(
    private val onItemSelector: OnItemClick<String>
) : ListAdapter<RecipeModelUI, RecipeListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val binding = LsvItemDishBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val model = getItem(holder.adapterPosition)
            onItemSelector.onItemClick(convertJson(model))
        }

        holder.bind(getItem(holder.adapterPosition))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipeModelUI>() {
            override fun areItemsTheSame(oldItem: RecipeModelUI, newItem: RecipeModelUI): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: RecipeModelUI, newItem: RecipeModelUI): Boolean =
                oldItem == newItem

        }
    }
}
