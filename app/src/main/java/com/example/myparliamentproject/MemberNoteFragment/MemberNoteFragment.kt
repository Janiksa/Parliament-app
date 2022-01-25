package com.example.myparliamentproject.MemberNoteFragment

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myparliamentproject.R
import com.example.myparliamentproject.databinding.MemberInfoFragmentBinding
import com.example.myparliamentproject.databinding.MemberNoteFragmentBinding

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class MemberNoteFragment : Fragment() {


    private lateinit var model: MemberNoteViewModel
    private lateinit var binding: MemberNoteFragmentBinding
    private lateinit var adapter: PartiesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<MemberNoteFragmentBinding>(
            inflater,
            R.layout.member_note_fragment,
            container,
            false
        )

        //puts party photos and names to recyclerview
        model = ViewModelProvider(this).get(MemberNoteViewModel::class.java)
        adapter = PartiesRecyclerViewAdapter(model.partyImages)
        binding.NoteRecyclerView.adapter = adapter
        binding.NoteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.NoteRecyclerView.setHasFixedSize(true)


        //when any of the parties are clicked, user will be moved to next fragment
        //where all members of that party are shown
        adapter.setOnItemCLickListener(object : PartiesRecyclerViewAdapter.PartyClickListener {
            override fun onPartyClick(position: Int) {
                val action =
                    MemberNoteFragmentDirections.actionMemberNoteFragmentToPartyFragment(position)
                view?.findNavController()?.navigate(action)
                Log.d("partyPosition", "$position")
            }
        })
        return binding.root
    }
}