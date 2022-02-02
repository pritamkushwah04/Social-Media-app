package com.example.socialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.socialapp.daos.PostDao
import com.example.socialapp.databinding.ActivityCreatPostBinding

class CreatPostActivity : AppCompatActivity() {

    private lateinit var postDao: PostDao
    private lateinit var binding:ActivityCreatPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreatPostBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        postDao = PostDao()
        binding.postButton.setOnClickListener {
            val input=binding.postInput.text.toString().trim()
            if(input.isNotEmpty()){
                postDao.addPost(input)
                finish()
            }
        }
    }
}