package com.bitcodetech.fragments5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContactDetailsFragment : Fragment() {

    private lateinit var imgContact: ImageView
    private lateinit var txtContactName : TextView
    private lateinit var txtContactNumber : TextView
    private lateinit var btnDeleteContact : Button
    private lateinit var btnUpdateContact : Button

    private var contact: Contact? = null

    interface OnContactActionListener {
        fun onContactDelete(contact : Contact)
        fun onContactUpdate(oldContact: Contact, newContact: Contact)
    }

    var onContactActionListener : OnContactActionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.contact_details_fragment, null)

        initViews(view)
        initData()
        setupListeners()

        return view
    }

    private fun setupListeners() {

        btnDeleteContact.setOnClickListener {
            onContactActionListener?.onContactDelete(
                contact!!
            )
            removeCurrentFragment()
        }

    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    private fun initData() {
        if(arguments != null && requireArguments().containsKey("contact") ) {
            contact = requireArguments().getSerializable("contact") as Contact

            imgContact.setImageResource(contact!!.imageId)
            txtContactName.text = contact!!.name
            txtContactNumber.text = contact!!.number
        }
    }

    private fun initViews(view : View) {
        imgContact = view.findViewById(R.id.imgContact)
        txtContactName = view.findViewById(R.id.txtContactName)
        txtContactNumber = view.findViewById(R.id.txtContactNumber)
        btnDeleteContact = view.findViewById(R.id.btnDeleteContact)
        btnUpdateContact = view.findViewById(R.id.btnUpdateContact)
    }

}