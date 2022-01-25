package com.example.myparliamentproject.MemberNoteFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myparliamentproject.R

/* Jani Salo
   ID: 2013109
   pvm: 27.9.2021
 */

//This recycler view adapter is used to make recycler view
//that contains photo of every party and a textview with party name
class PartiesRecyclerViewAdapter(private val allPartyPhotos: List<Int>) :
    RecyclerView.Adapter<PartiesRecyclerViewAdapter.ViewHolder>() {

    private lateinit var partyListener: PartyClickListener

    interface PartyClickListener {
        fun onPartyClick(position: Int)
    }

    fun setOnItemCLickListener(listener: PartyClickListener) {
        partyListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.party_list_layout, parent, false)

        return ViewHolder(itemView, partyListener)
    }

    //Checks what photo is used in which view, and adds right party name
    //after that photo
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setBackgroundResource(allPartyPhotos[position])
        when (allPartyPhotos[position]) {
            R.drawable.ic_kdp -> holder.textView.text = "Kristillisdemokraatit"
            R.drawable.ic_kesk -> holder.textView.text = "Keskusta"
            R.drawable.ic_kok -> holder.textView.text = "Kokoomus"
            R.drawable.ic_liik -> holder.textView.text = "Liike Nyt"
            R.drawable.ic_ps -> holder.textView.text = "Perussuomalaiset"
            R.drawable.ic_rkp -> holder.textView.text = "Suomenruotsalainen kansanpuolue"
            R.drawable.ic_sdp -> holder.textView.text = "Sosiaalidemokraattinen puolue"
            R.drawable.ic_vas -> holder.textView.text = "Vasemmistoliitto"
            R.drawable.ic_vihr -> holder.textView.text = "Vihreät"
        }
    }

    override fun getItemCount() = allPartyPhotos.size

    class ViewHolder(itemView: View, listener: PartyClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.partyImageView)
        val textView: TextView = itemView.findViewById(R.id.partyListPartyName)

        init {
            itemView.setOnClickListener { listener.onPartyClick(adapterPosition) }
        }
    }
}