package com.example.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.studentdata.StudentDatabase
import com.example.room.studentdata.StudentRepository
import com.example.room.studentdata.StudentViewModelFactory
import com.example.room.studentdata.StudentViewModel

class ListFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list2, container, false)

            //recyclerview
        val adapter=StudentAdapter()
        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        //studentViewModel
        studentViewModel=ViewModelProvider(this).get(StudentViewModel::class.java)
        studentViewModel.readAllData.observe(viewLifecycleOwner, Observer { student->
            adapter.setData(student)
        })
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener {
            findNavController(view).navigate(R.id.action_listFragment_to_addFragment)
        }
        //add menu
        setHasOptionsMenu(true)
        return view
    }
}