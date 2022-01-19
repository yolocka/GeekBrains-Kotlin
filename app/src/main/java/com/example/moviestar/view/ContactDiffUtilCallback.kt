package com.example.moviestar.view

import androidx.recyclerview.widget.DiffUtil
import com.example.moviestar.model.ContactDTO
import com.example.moviestar.model.Movie

class ContactDiffUtilCallback (private val oldList: List<ContactDTO>, private val newList: List<ContactDTO>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldContact: ContactDTO = oldList[oldItemPosition]
        val newContact: ContactDTO = newList[newItemPosition]
        return oldContact == newContact
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie: ContactDTO = oldList[oldItemPosition]
        val newMovie: ContactDTO = newList[newItemPosition]
        return (oldMovie.name == newMovie.name
                && oldMovie.phone == newMovie.phone)
    }
}