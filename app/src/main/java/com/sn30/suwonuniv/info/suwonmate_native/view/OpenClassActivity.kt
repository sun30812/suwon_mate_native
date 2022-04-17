package com.sn30.suwonuniv.info.suwonmate_native.view

import android.content.Intent
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
import com.sn30.suwonuniv.info.suwonmate_native.models.ClassDetailInfo
import com.sn30.suwonuniv.info.suwonmate_native.models.SettingStore

class OpenClassActivity : AppCompatActivity() {
    private val TAG = "FirebaseTest"
    private lateinit var binding: OpenClassBinding
    private lateinit var majorDataSnapshot: DataSnapshot
    private lateinit var pref: SettingStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SettingStore(this)
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
        binding.grades.setSelection(pref.getInt("grade", 0))
        var department = pref.getString("department", "컴퓨터학부")
        var major = pref.getString("major", "컴퓨터SW")
        var grade = pref.getInt("grade", 0)
        val subjectList: ArrayList<DataSnapshot> = arrayListOf()
        val adapter = OpenClassListViewAdapter(subjectList)
        adapter.setOnItemClickListener(object : OpenClassListViewAdapter.OnItemClickListener {
            override fun onItemClicked(v: View, data: ClassDetailInfo) {
                val intent = Intent(this@OpenClassActivity, OpenClassDetailActivity::class.java)
                Log.d(TAG, "onItemClicked: $data")
                intent.putExtra("data", data)
                startActivity(intent)
            }

        })
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
                    if (department == it.key.toString()) {
                        binding.departments.setSelection(departmentsListAdapter.count - 1)
                    }
                }
                binding.spinnerGroup.visibility = View.VISIBLE

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
                binding.dataLoading.visibility = View.VISIBLE
                department = binding.departments.selectedItem.toString()
                majorListAdapter.clear()
                majorListAdapter.add("전체")
                binding.majors.setSelection(0)
                subjectList.clear()
                majorDataSnapshot.child(binding.departments.selectedItem.toString()).children.forEach { dat ->
                    majorListAdapter.add(dat.value.toString())
                    if (major == dat.value.toString()) {
                        binding.majors.setSelection(majorListAdapter.count - 1)
                    }
                }
                mySubjectRef.child(department).orderByChild("trgtGrdeCd")
                    .equalTo(grade + 1.toDouble())
                    .addValueEventListener(object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {
                            snapshot.children.forEach {
                                if (major != "전체") {
                                    if (it.child("estbMjorNm").value.toString() == major) {
                                        subjectList.add(it)
                                    }
                                } else {
                                    subjectList.add(it)
                                }
                                adapter.notifyDataSetChanged()
                            }
                            binding.dataLoading.visibility = View.GONE
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.w(TAG, "Can't access because: $error")
                        }
                    })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.majors.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                binding.dataLoading.visibility = View.VISIBLE
                major = binding.majors.selectedItem.toString()
                subjectList.clear()
                mySubjectRef.child(department).orderByChild("trgtGrdeCd")
                    .equalTo(grade + 1.toDouble())
                    .addValueEventListener(object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {
                            snapshot.children.forEach {
                                if (major != "전체") {
                                    if (it.child("estbMjorNm").value.toString() == major) {
                                        subjectList.add(it)
                                    }
                                } else {
                                    subjectList.add(it)
                                }
                                adapter.notifyDataSetChanged()
                            }
                            binding.dataLoading.visibility = View.GONE
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.w(TAG, "Can't access because: $error")
                        }
                    })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.grades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                binding.dataLoading.visibility = View.VISIBLE
                grade = p2
                subjectList.clear()
                mySubjectRef.child(department).orderByChild("trgtGrdeCd").equalTo(grade + 1.toDouble()).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            subjectList.add(
                                it
                            )
                            adapter.notifyDataSetChanged()
                        }
                            binding.dataLoading.visibility = View.GONE
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