package com.example.room

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.studentdata.Student
import com.example.room.studentdata.StudentViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var sViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        sViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.findViewById<EditText>(R.id.updatefirstName).setText(args.currentStudent.firstName)
        view.findViewById<EditText>(R.id.updatelastName).setText(args.currentStudent.lastName)
        view.findViewById<EditText>(R.id.updatemks).setText(args.currentStudent.marks.toString())

        view.findViewById<Button>(R.id.update).setOnClickListener {
            updateStudent()
        }

        //Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateStudent() {
        val firstName = view?.findViewById<EditText>(R.id.updatefirstName)?.text.toString()
        val lastName = view?.findViewById<EditText>(R.id.updatelastName)?.text.toString()
        val marks = view?.findViewById<EditText>(R.id.updatemks)?.text

        if (inputCheck(firstName, lastName, marks)) {

            //create student database
            val updatedStudent = Student(0, firstName, lastName, Integer.parseInt(marks.toString()))
            //add data to database
            sViewModel.updateStudent(updatedStudent)
            Toast.makeText(requireContext(), "successful update", Toast.LENGTH_LONG).show()

            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "fill all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, marks: Editable?): Boolean {
        return !(TextUtils.isEmpty(firstName))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete){
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteStudent() {
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_, _ ->
            sViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(requireContext(),"successful removal",Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentStudent.firstName}?")
        builder.setMessage("Are you sure to delete ${args.currentStudent.firstName}?")
        builder.create().show()
    }
}