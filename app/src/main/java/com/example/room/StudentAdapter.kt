package com.example.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
//import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room.studentdata.Student

class StudentAdapter:RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentList= emptyList<Student>()

    class StudentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_file,parent,false))
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentItem=studentList[position]
        holder.itemView.findViewById<TextView>(R.id.id_txt).text=currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.fname_txt).text=currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.lname_txt).text=currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.mks_txt).text=currentItem.marks.toString()

        holder.itemView.findViewById<ViewGroup>(R.id.studentLayout).setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    fun setData(student: List<Student>){
        this.studentList=student
        notifyDataSetChanged()
    }
}