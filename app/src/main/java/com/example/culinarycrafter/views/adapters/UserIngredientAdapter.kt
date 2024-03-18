package com.example.culinarycrafter.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.culinarycrafter.R
import com.example.culinarycrafter.models.entities.UserIngredientEntity

class UserIngredientAdapter(private val listener: OnClickDeleteListener) :
    ListAdapter<UserIngredientEntity, UserIngredientAdapter.UserIngredientHolder>(
        UserIngredientComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserIngredientHolder {
        return UserIngredientHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserIngredientHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class UserIngredientHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.ingredientTextView)
        private val deleteImageView: ImageView = itemView.findViewById(R.id.deleteImageView)


        fun bind(userIngredient: UserIngredientEntity, listener: OnClickDeleteListener) {
            textView.text = userIngredient.ingredient
            deleteImageView.setOnClickListener {
                listener.onClickDelete(userIngredient)
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserIngredientHolder {
                return UserIngredientHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.user_ingredient_item, parent, false)
                )
            }
        }

    }

    class UserIngredientComparator : DiffUtil.ItemCallback<UserIngredientEntity>() {
        override fun areItemsTheSame(
            oldItem: UserIngredientEntity,
            newItem: UserIngredientEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UserIngredientEntity,
            newItem: UserIngredientEntity
        ): Boolean {
            return oldItem.ingredient == newItem.ingredient
        }

    }

    interface OnClickDeleteListener {

        fun onClickDelete(userIngredient: UserIngredientEntity)

    }


}