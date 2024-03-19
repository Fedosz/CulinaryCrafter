package com.example.culinarycrafter.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.culinarycrafter.R
import com.example.culinarycrafter.models.enums.IngredientEnum
import com.google.android.material.button.MaterialButton

class FindIngredientAdapter(private val listener: OnClickItemListener) :
    ListAdapter<IngredientEnum, FindIngredientAdapter.FindIngredientHolder>(
        FindIngredientComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindIngredientHolder {
        return FindIngredientHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FindIngredientHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class FindIngredientHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientTextView: TextView = itemView.findViewById(R.id.ingredientTextView)
        private val button: MaterialButton = itemView.findViewById(R.id.button)


        fun bind(ingredient: IngredientEnum, listener: OnClickItemListener) {
            ingredientTextView.text = ingredient.value
            button.setOnClickListener {
                listener.onClickItem(ingredient)
            }

        }

        companion object {
            fun create(parent: ViewGroup): FindIngredientHolder {
                return FindIngredientHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.ingredient_item, parent, false)
                )
            }
        }

    }

    class FindIngredientComparator : DiffUtil.ItemCallback<IngredientEnum>() {
        override fun areItemsTheSame(
            oldItem: IngredientEnum,
            newItem: IngredientEnum
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: IngredientEnum,
            newItem: IngredientEnum
        ): Boolean {
            return oldItem.value == newItem.value
        }

    }

    interface OnClickItemListener {

        fun onClickItem(ingredient: IngredientEnum)

    }


}