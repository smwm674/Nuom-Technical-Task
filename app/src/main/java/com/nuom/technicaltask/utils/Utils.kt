package com.nuom.technicaltask.utils

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.nuom.technicaltask.R
import com.nuom.technicaltask.data.model.Joke


object Utils {
    fun setText(textView: TextView, value: String) {
        textView.text = value
    }

    fun showRandomJoke(joke: Joke, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.random_joke)
        builder.setMessage(joke.value)
        builder.setNeutralButton(R.string.ok) { dialog, which ->
        }
        builder.show()
    }
}