package com.example.myparliamentproject.MemberInfoFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myparliamentproject.Notes.Notes
import com.example.myparliamentproject.R

/* Jani Salo
   ID: 2013109
   pvm: 30.9.2021
 */

class NotesRecyclerViewAdapter: RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>() {

    private var notes: List<Notes> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.party_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.textView.text = notes[position].note

    }

    override fun getItemCount() = notes.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.partyName)

    }

    fun setData(note: List<Notes>){
        this.notes = note
        notifyDataSetChanged()
    }
}