package com.nuom.technicaltask.ui.main_activity.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nuom.technicaltask.data.model.Joke
import com.nuom.technicaltask.databinding.JokeItemBinding
import com.nuom.technicaltask.utils.Utils.setText

class JokeAdaptor(
    private var jokeList: ArrayList<Joke>
) : RecyclerView.Adapter<JokeAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(jokeList.get(position))
    }

    override fun getItemCount() = jokeList.size

    inner class ViewHolder(val binding: JokeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Joke) {
            binding.apply {
                setText(joke, data.value)
            }
        }
    }
}
