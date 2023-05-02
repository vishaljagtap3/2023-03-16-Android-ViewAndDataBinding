package com.bitcodetech.fragments5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(
    val contactsList: ArrayList<Contact>
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    interface OnContactClickListener {
        fun onContactClick(contactsAdapter : ContactsAdapter, view : View, contact : Contact, position : Int)
    }

    var onContactClickListener : OnContactClickListener? = null

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgContact: ImageView
        val txtContactName: TextView
        val txtContactNumber: TextView

        init {
            imgContact = view.findViewById(R.id.imgContact)
            txtContactName = view.findViewById(R.id.txtContactName)
            txtContactNumber = view.findViewById(R.id.txtContactNumber)

            view.setOnClickListener {
                onContactClickListener?.onContactClick(
                    this@ContactsAdapter,
                    view,
                    contactsList[adapterPosition],
                    adapterPosition
                )
            }
        }

    }

    override fun getItemCount() = contactsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_view, null)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactsList[position]

        holder.imgContact.setImageResource(contact.imageId)
        holder.txtContactName.text = contact.name
        holder.txtContactNumber.text = contact.number
    }
}