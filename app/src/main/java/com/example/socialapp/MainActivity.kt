package com.example.socialapp

/*
import android.app.DownloadManager
*/
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialapp.daos.PostDao

import com.example.socialapp.databinding.ActivityCreatPostBinding

import com.example.socialapp.databinding.ActivityMainBinding
import com.example.socialapp.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), IPostAdapter {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.fab.setOnClickListener{
            val intent =Intent(this,CreatPostActivity::class.java)
            startActivity(intent)
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
         postDao = PostDao()
         val postsCollections = postDao.postCollections
         val query=postsCollections.orderBy("createdAt", Query.Direction.DESCENDING)
         val recyclerViewOptions= FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

         adapter= PostAdapter(recyclerViewOptions,this )
         binding.recyclerView.adapter =adapter
         binding.recyclerView.layoutManager=LinearLayoutManager(this)



    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes(postId)
    }
}