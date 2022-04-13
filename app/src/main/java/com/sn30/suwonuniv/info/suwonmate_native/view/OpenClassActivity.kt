package com.sn30.suwonuniv.info.suwonmate_native.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.sn30.suwonuniv.info.suwonmate_native.databinding.OpenClassBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class OpenClassActivity : AppCompatActivity() {
    private val TAG = "FirebaseTest"
    private lateinit var binding: OpenClassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpenClassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        CoroutineScope(Dispatchers.Main).launch {
            getDB()
        }
    }
    private suspend fun getDB(): Boolean = withContext(Dispatchers.IO) {
        val database = Firebase.database
        val myRef = database.getReference("estbLectDtaiList_test/0/컴퓨터학부")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
//                val subjectList = JSONObject(value.toString())
                Log.d(TAG, "value is ${value}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "onCancelled: ", error.toException())
            }
        })
        true
    }

}