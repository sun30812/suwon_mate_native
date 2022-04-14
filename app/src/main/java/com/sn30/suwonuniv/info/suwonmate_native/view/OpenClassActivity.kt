package com.sn30.suwonuniv.info.suwonmate_native.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.adapter.OpenClassListViewAdapter
import com.sn30.suwonuniv.info.suwonmate_native.databinding.OpenClassBinding
import com.sn30.suwonuniv.info.suwonmate_native.models.CardItem
import com.sn30.suwonuniv.info.suwonmate_native.models.InfoCardItem
import com.sn30.suwonuniv.info.suwonmate_native.models.SimpleCardItem

class OpenClassActivity : AppCompatActivity() {
    private val TAG = "FirebaseTest"
    private lateinit var binding: OpenClassBinding
    private lateinit var majorDataSnapshot: DataSnapshot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpenClassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val departmentsListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line)
        val majorListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line)
        val gradeListAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,resources.getStringArray(
            R.array.grades))
        binding.departments.adapter = departmentsListAdapter
        binding.majors.adapter = majorListAdapter
        binding.grades.adapter = gradeListAdapter
        var department = ""
        var major = ""
        val subjectList: ArrayList<SimpleCardItem> = arrayListOf()
        val adapter = OpenClassListViewAdapter(subjectList)
        binding.classListView.adapter = adapter
        binding.classListView.layoutManager = LinearLayoutManager(this)
        val database = Firebase.database
        val myRef = database.getReference("/departments")
        val mySubjectRef = database.getReference("/estbLectDtaiList")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                majorDataSnapshot = snapshot
                majorDataSnapshot.children.forEach {
                    departmentsListAdapter.add(it.key.toString())
                }
                binding.spinnerGroup.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "onCancelled: ", error.toException())
            }
        })
        binding.departments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                department = binding.departments.selectedItem.toString()
                majorListAdapter.clear()
                subjectList.clear()
                majorDataSnapshot.child(binding.departments.selectedItem.toString()).children.forEach { dat ->
                    majorListAdapter.add(dat.value.toString())
                }
                mySubjectRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.child(department).children.forEach {
                            subjectList.add(
                                SimpleCardItem(
                                    it.child("subjtNm").value.toString(),
                                    it.child("ltrPrfsNm").value.toString()
                                )
                            )
                            adapter.notifyDataSetChanged()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(TAG, "Can't access because: $error")
                    }
                })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}