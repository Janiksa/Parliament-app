package com.example.myparliamentproject.MemberInfoFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myparliamentproject.Notes.Notes
import com.example.myparliamentproject.R
import com.example.myparliamentproject.databinding.MemberInfoFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class MemberInfoFragment : Fragment() {

    private lateinit var binding: MemberInfoFragmentBinding
    private val args: MemberInfoFragmentArgs by navArgs()
    private lateinit var adapter: NotesRecyclerViewAdapter
    private var score: Int = 0


    private lateinit var model: MemberInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<MemberInfoFragmentBinding>(
            inflater,
            R.layout.member_info_fragment,
            container,
            false
        )

        //Position is member number from last fragment.
        //it is used to fetch data from that member
        val position = args.memberNumber

        model = ViewModelProvider(this).get(MemberInfoViewModel::class.java)

        //Defining adapter for recycler view that shows all of the notes for that member
        adapter = NotesRecyclerViewAdapter()
        binding.memberInfoRecyclerView.adapter = adapter
        binding.memberInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.memberInfoRecyclerView.setHasFixedSize(true)

        //If there is notes in database already, they will be put to recycler view
        try {
            model.loadNotes(position).observe(viewLifecycleOwner, { notes -> this.adapter.setData(notes)})
            Log.d("NotesToList", "Trying to load notes")
        }
        catch (e: Exception){
            Log.d("NotesToListFail", e.toString())
        }

        //Saves note from edit text to database
        //Sets edit text empty after text is saved to database
        binding.button4.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val editTextNote = binding.insertNoteEditText.text.toString()
                val note = Notes(position, editTextNote)
                model.insertNotes(note)
                binding.insertNoteEditText.setText("")
            }
        })

        return binding.root
    }
}

