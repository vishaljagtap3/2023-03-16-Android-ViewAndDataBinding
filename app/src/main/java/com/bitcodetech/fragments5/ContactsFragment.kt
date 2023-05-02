package com.bitcodetech.fragments5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactsFragment : Fragment() {

    private lateinit var recyclerContacts : RecyclerView
    private lateinit var contactsList : ArrayList<Contact>
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var btnAddContact : FloatingActionButton

    private var contactActionPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.contacts_fragment, null)

        initData()
        initViews(view)
        setupListeners()

        return view
    }

    private fun setupListeners() {
        contactsAdapter.onContactClickListener =
            object : ContactsAdapter.OnContactClickListener {
                override fun onContactClick(
                    contactsAdapter: ContactsAdapter,
                    view: View,
                    contact: Contact,
                    position: Int
                ) {
                    mt("${contact.name} clicked")

                    contactActionPosition = position
                    addContactDetailsFragment(contact)
                }
            }

        btnAddContact.setOnClickListener {
            addAddContactFragment()
        }
    }

    private fun addAddContactFragment() {
        val addContactFragment = AddContactFragment()

        addContactFragment.onContactAddedListener =
            object : AddContactFragment.OnContactAddedListener {
                override fun onContactAdded(contact: Contact) {
                    contactsList.add(contact)
                    contactsAdapter.notifyItemInserted(contactsList.size-1)
                }
            }


        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, addContactFragment, AddContactFragment::class.java.name)
            .addToBackStack(AddContactFragment::class.java.name)
            .commit()
    }

    private fun addContactDetailsFragment(contact: Contact) {

        val contactDetailsFragment = ContactDetailsFragment()

        val input = Bundle()
        input.putSerializable("contact", contact)
        contactDetailsFragment.arguments = input

        contactDetailsFragment.onContactActionListener = MyOnContactActionListener()

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, contactDetailsFragment, null)
            .addToBackStack(null)
            .commit()

    }


    private inner class MyOnContactActionListener : ContactDetailsFragment.OnContactActionListener {
        override fun onContactDelete(contact: Contact) {
            contactsList.remove(contact)
            contactsAdapter.notifyItemRemoved(contactActionPosition)
            contactActionPosition = -1
        }

        override fun onContactUpdate(oldContact: Contact, newContact: Contact) {

        }
    }

    private fun mt(text : String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    private fun initData() {
        contactsList = ArrayList<Contact>()
        for(i in 0..30) {
            contactsList.add(
                Contact(
                    i,
                    "${ (65+i).toChar()} ${(65+i).toChar()}",
                    "$i$i",
                    R.mipmap.ic_launcher
                )
            )
        }
    }

    private fun initViews(view : View) {

        btnAddContact = view.findViewById(R.id.btnAddContact)

        recyclerContacts = view.findViewById(R.id.recyclerContacts)
        recyclerContacts.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        contactsAdapter = ContactsAdapter(contactsList)
        recyclerContacts.adapter = contactsAdapter
    }

}