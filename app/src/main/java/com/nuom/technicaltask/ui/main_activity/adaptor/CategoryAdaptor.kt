package com.nuom.technicaltask.ui.main_activity.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nuom.technicaltask.databinding.CateogryItemBinding
import com.nuom.technicaltask.utils.Utils.setText

class CategoryAdaptor(
    private var list: ArrayList<String>,
    private var listener: categoryListAction
) : RecyclerView.Adapter<CategoryAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CateogryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }

    override fun getItemCount() = list.size


    interface categoryListAction {
        fun onClick(data: String)
    }

    inner class ViewHolder(val binding: CateogryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            binding.apply {
                setText(category, data)
                category.setOnClickListener(View.OnClickListener { view -> listener.onClick(data) })
            }
        }
    }
}
