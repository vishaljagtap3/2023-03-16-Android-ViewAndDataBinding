package com.bitcodetech.fragments5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bitcodetech.fragments5.databinding.ContactDetailsFragmentBinding

class ContactDetailsFragment : Fragment() {

    private lateinit var binding : ContactDetailsFragmentBinding
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
        //binding = ContactDetailsFragmentBinding.inflate(inflater)
        binding = ContactDetailsFragmentBinding.bind(view)


        initData()
        setupListeners()

        return view
        //return binding.root
    }

    private fun setupListeners() {

        binding.btnDeleteContact.setOnClickListener {
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

            binding.contact = contact
            binding.imgContact.setImageResource(contact!!.imageId)

            /*binding.imgContact.setImageResource(contact!!.imageId)
            binding.txtContactName.text = contact!!.name
            binding.txtContactNumber.text = contact!!.number*/
        }
    }



}