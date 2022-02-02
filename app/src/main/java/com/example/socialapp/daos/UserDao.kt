package com.example.socialapp.daos

import com.example.socialapp.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    private val dp= FirebaseFirestore.getInstance()
    private val usersCollection= dp.collection("users")

    fun addUser(user: User?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO){
                usersCollection.document(user.uid).set(it)
            }
        }
    }

    fun getUserBYId(uId: String): Task<DocumentSnapshot>{
        return usersCollection.document(uId).get()
    }
}