package com.example.myparliamentproject.PartyFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myparliamentproject.Data.RecyclerViewAdapter
import com.example.myparliamentproject.R
import com.example.myparliamentproject.databinding.PartyFragmentBinding


/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class PartyFragment : Fragment() {

    //variable args sets up the args, so data from last fragment
    //can be received
    private val args: PartyFragmentArgs by navArgs()


    private lateinit var binding: PartyFragmentBinding
    private lateinit var adapter: RecyclerViewAdapter
    lateinit var model: PartyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //defines data binding for the fragment
        binding = DataBindingUtil.inflate<PartyFragmentBinding>(
            inflater,
            R.layout.party_fragment,
            container,
            false
        )

        model = ViewModelProvider(this).get(PartyViewModel::class.java)

        //variable party position contains party position data
        //from last fragment, it is used to fetch parliament members
        //from right party
        val partyPosition = args.partyPosition

        //Function that changes party list position argument to string which can be used
        //as argument to fetch parliament members from database
        fun partyString(): String {
            var partyToShow: String = ""
            when (partyPosition) {
                0 -> partyToShow = "kd"
                1 -> partyToShow = "kesk"
                2 -> partyToShow = "kok"
                3 -> partyToShow = "liik"
                4 -> partyToShow = "ps"
                5 -> partyToShow = "r"
                6 -> partyToShow = "sd"
                7 -> partyToShow = "vas"
                8 -> partyToShow = "vihr"
            }
            return partyToShow
        }

        adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        //inputs parliament members from wanted party to recyclerview
        model.loadPartyMembers(partyString())
            .observe(viewLifecycleOwner, { members -> this.adapter.setData(members) })

        //Makes click listener for each parliament member, opens next fragment where
        //parliament members info is shown.
        //sends parliament members person number to next fragment and it is used as
        //argument to fetch chosen parliament member information
        adapter.SetOnItemClickListener(object : RecyclerViewAdapter.MemberClickListener {
            override fun onMemberClick(position: Int) {
                val action = PartyFragmentDirections.actionPartyFragmentToMemberFragment(position)
                view?.findNavController()?.navigate(action)
                Log.d("positionForFragment", "$position")
            }
        })
        return binding.root
    }
}

