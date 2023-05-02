package com.bitcodetech.fragments5

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment

class AddContactFragment : Fragment() {

    private lateinit var imgContact : ImageView
    private lateinit var edtContactName : EditText
    private lateinit var edtContactNumber : EditText
    private lateinit var btnSaveContact : Button

    private var imageId = R.mipmap.ic_launcher

    interface OnContactAddedListener {
        fun onContactAdded(contact : Contact)
    }

    var onContactAddedListener : OnContactAddedListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.add_contact_fragment, null)

        initViews(view)
        setupListeners()

        return view
    }

    private fun setupListeners() {

        imgContact.setOnClickListener {

            val images = ArrayList<Int>()
            images.add(R.mipmap.ic_launcher)
            images.add(R.drawable.bitcode)
            images.add(R.drawable.flag_france)
            images.add(R.drawable.flag)
            images.add(R.drawable.flag_ind)
            images.add(R.drawable.flag_germany)
            images.add(R.mipmap.ic_launcher)
            images.add(R.drawable.bitcode)
            images.add(R.drawable.flag_france)
            images.add(R.drawable.flag)
            images.add(R.drawable.flag_ind)
            images.add(R.drawable.flag_germany)
            images.add(R.mipmap.ic_launcher)
            images.add(R.drawable.bitcode)
            images.add(R.drawable.flag_france)
            images.add(R.drawable.flag)
            images.add(R.drawable.flag_ind)
            images.add(R.drawable.flag_germany)

            val imageActivityIntent = Intent(context, ImagesActivity::class.java)
            imageActivityIntent.putExtra("image_ids", images)

            startActivityForResult(
                imageActivityIntent,
                1
            )
        }


        btnSaveContact.setOnClickListener {
            val contact = Contact(
                1,
                edtContactName.text.toString(),
                edtContactNumber.text.toString(),
                imageId
            )
            onContactAddedListener?.onContactAdded(contact)
            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    private fun initViews(view : View) {
        imgContact = view.findViewById(R.id.imgContact)
        edtContactName = view.findViewById(R.id.edtContactName)
        edtContactNumber = view.findViewById(R.id.edtContactNumber)
        btnSaveContact = view.findViewById(R.id.btnSaveContact)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null) {
            return
        }

        imageId = data.getIntExtra("image_id", R.mipmap.ic_launcher)
        imgContact.setImageResource(imageId)
    }

}