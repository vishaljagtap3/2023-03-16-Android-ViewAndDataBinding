package com.bitcodetech.fragments5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImagesActivity : AppCompatActivity() {

    private lateinit var recyclerImages : RecyclerView
    private lateinit var imageIds : ArrayList<Int>

    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.images_activity)

        initData()
        initViews()
        setupListeners()

    }

    private fun setupListeners() {
        imagesAdapter.onImageClickListener =
            object : ImagesAdapter.OnImageClickListener {
                override fun onImageClick(
                    imagesAdapter: ImagesAdapter,
                    imageId: Int,
                    position: Int
                ) {
                    val resIntent = Intent()
                    resIntent.putExtra("image_id", imageId)
                    setResult(1, resIntent)

                    finish()
                }
            }
    }

    private fun initData() {
        imageIds = intent.getSerializableExtra("image_ids") as ArrayList<Int>
    }

    private fun initViews() {
        recyclerImages = findViewById(R.id.recyclerImages)
        recyclerImages.layoutManager = GridLayoutManager(
            this, 3
        )

        imagesAdapter = ImagesAdapter(imageIds)
        recyclerImages.adapter = imagesAdapter

    }

}