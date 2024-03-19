package com.example.culinarycrafter.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.culinarycrafter.R
import com.example.culinarycrafter.models.enums.CategoryEnum

class CategoryAdapter(private val listener: OnCheckListener) :
    ListAdapter<CategoryEnum, CategoryAdapter.CategoryHolder>(
        CategoryComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryCheckBox: CheckBox = itemView.findViewById(R.id.categoryCheckBox)

        fun bind(category: CategoryEnum, listener: OnCheckListener) {
            categoryCheckBox.text = category.value
            categoryCheckBox.isChecked = category.isChecked
            categoryCheckBox.setOnClickListener {
                listener.onChecked(category)
            }
        }

        companion object {
            fun create(parent: ViewGroup): CategoryHolder {
                return CategoryHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.category_item, parent, false)
                )
            }
        }
    }

    class CategoryComparator : DiffUtil.ItemCallback<CategoryEnum>() {
        override fun areItemsTheSame(
            oldItem: CategoryEnum,
            newItem: CategoryEnum
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoryEnum,
            newItem: CategoryEnum
        ): Boolean {
            return oldItem.value == newItem.value
        }

    }

    interface OnCheckListener {

        fun onChecked(category: CategoryEnum)

    }


}