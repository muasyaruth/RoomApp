package com.example.room

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.studentdata.Student
import com.example.room.studentdata.StudentViewModel


class AddFragment : Fragment() {
    private lateinit var sViewModel:StudentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add2, container, false)
        sViewModel=ViewModelProvider(this).get(StudentViewModel::class.java)

        view.findViewById<Button>(R.id.addDetails).setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName= view?.findViewById<EditText>(R.id.addFirstName)?.text.toString()
        val lastName= view?.findViewById<EditText>(R.id.addLastName)?.text.toString()
        val marks= view?.findViewById<EditText>(R.id.addMarks)?.text

        if(inputCheck(firstName,lastName,marks)){

            //create student database
            val student=Student(0,firstName,lastName,Integer.parseInt(marks.toString()))
            //add data to database
            sViewModel.addStudent(student)
            Toast.makeText(requireContext(),"successful add",Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"fill all fields",Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(firstName:String, lastName: String, marks: Editable?):Boolean{
        return !(TextUtils.isEmpty(firstName))
    }

}