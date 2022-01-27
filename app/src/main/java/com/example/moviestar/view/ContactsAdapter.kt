package com.example.moviestar.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestar.R
import com.example.moviestar.model.ContactDTO

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    private var contacts: List<ContactDTO> = listOf()
    var listener: OnItemClick? = null

    fun setContacts(data: List<ContactDTO>) {
        val mainDiffUtilCallback = ContactDiffUtilCallback(contacts, data)
        val productDiffResult = DiffUtil.calculateDiff(mainDiffUtilCallback)
        contacts = data
        productDiffResult.dispatchUpdatesTo(this);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size


    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(contact: ContactDTO) {
            itemView.apply {
                findViewById<TextView>(R.id.contact_name).text = contact.name
                findViewById<TextView>(R.id.contact_number).text = contact.phone
                setOnClickListener{
                    listener?.onClick(contact)
                }
            }
        }
    }

    fun interface OnItemClick {
        fun onClick(contact: ContactDTO)
    }
}