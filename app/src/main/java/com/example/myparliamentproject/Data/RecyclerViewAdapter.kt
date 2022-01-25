package com.example.myparliamentproject.Data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myparliamentproject.PartyFragment.PartyFragmentDirections
import com.example.myparliamentproject.R

/* Jani Salo
   ID: 2013109
   pvm: 26.9.2021
 */

//RecyclerView adapter for listing members after one party is chosen in application
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var members: List<ParliamentMember> = emptyList()
    private lateinit var memberListener: MemberClickListener

    interface MemberClickListener {
        fun onMemberClick(position: Int)

    }

    //Onclick function for each view in recycler view
    fun SetOnItemClickListener(listener: MemberClickListener) {
        memberListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.party_layout, parent, false)

        return ViewHolder(itemView, memberListener)
    }

    //Defines what is showed in recycler view
    //Following code takes members first and last name and shows them
    //After clicking member that member person number is sent to next fragment
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = members[position].first + " " + members[position].last
        val number = members[position].personNumber
        holder.textView.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(PartyFragmentDirections.actionPartyFragmentToMemberFragment(number))
        }
    }

    override fun getItemCount() = members.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View, listener: MemberClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val textView: TextView = itemView.findViewById(R.id.partyName)

        init {
            itemView.setOnClickListener { listener.onMemberClick(adapterPosition) }
        }

    }

    //Sets data from list to recycler view, setData() is used
    //while observing livedata list
    fun setData(parliamentMember: List<ParliamentMember>) {
        this.members = parliamentMember
        notifyDataSetChanged()
    }
}