package com.example.myparliamentproject.MemberFragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myparliamentproject.Notes.Score
import com.example.myparliamentproject.R
import com.example.myparliamentproject.databinding.MemberFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

//This fragment shows all information from one particular member
class MemberFragment : Fragment() {

    //Defining args, which catches member number from
    //last fragment
    val args: MemberFragmentArgs by navArgs()
    private lateinit var model: MemberViewModel
    private lateinit var binding: MemberFragmentBinding
    var score = 0


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<MemberFragmentBinding>(
            inflater,
            R.layout.member_fragment,
            container,
            false
        )
        model = ViewModelProvider(this).get(MemberViewModel::class.java)

        //Position is member number that is clicked from last fragment
        val position = args.memberNumber

        //Fetching member score from database
        CoroutineScope(Dispatchers.IO).launch {
            score = model.getScoreInt(position)
        }
        //Puts member first and last name to text view
        model.loadOneMember(position).observe(
            viewLifecycleOwner,
            { member -> binding.MemberName.text = (member[0].first + " " + member[0].last) })

        //Puts member constituency to text view
        model.loadOneMember(position).observe(
            viewLifecycleOwner,
            { member -> binding.MemberConstituency.text = (member[0].constituency) })

        //Puts member party name to text view,
        //Party names are shortened in database, so they are changed to
        //Right names is when loop
        val setPartyName = binding.MemberParty
        model.loadOneMember(position).observe(viewLifecycleOwner, { member ->
            when (member[0].party) {
                "liik" -> setPartyName.text = "Liike Nyt"
                "kesk" -> setPartyName.text = "Keskusta"
                "kd" -> setPartyName.text = "Kristillisdemokraatit"
                "kok" -> setPartyName.text = "Kokoomus"
                "ps" -> setPartyName.text = "Perussuomalaiset"
                "r" -> setPartyName.text = "Suomenruotsalainen kansanpuolue"
                "vihr" -> setPartyName.text = "Vihreät"
                "vas" -> setPartyName.text = "Vasemmistoliitto"
                "sd" -> setPartyName.text = "Sosiaalidemokraattinen puolue"
            }
        })

        //Checks if member is minister, and depending if is or not
        //text view is assigned as "Minister" or "Member of parliament"
        val ifMinister = binding.Minister
        model.loadOneMember(position).observe(viewLifecycleOwner, { member ->
            if (member[0].minister) {
                ifMinister.text = "Minister"
            } else {
                ifMinister.text = "Member of parliament"
            }
        })

        //Puts score to text view, if there is not score for that member yet,
        //text view is set to 0 else it will put the score from database to textview
        model.getScore(position).observe(viewLifecycleOwner, { score ->
            binding.textView.text =
                "SCORE: " + if (score == null) {
                    "0"
                } else {
                    "$score"
                }
        })

        //Puts members age to textview, Database holds members born year but not age,
        //so age is calculated using current year and subtracted born year from that
        model.loadOneMember(position).observe(
            viewLifecycleOwner,
            { member -> binding.MemberAge.text = (2021 - member[0].bornYear).toString() })

        //Puts members seat number to text view
        model.loadOneMember(position).observe(
            viewLifecycleOwner,
            { member ->
                binding.MemberSeatNumber.text =
                    ("Seat number:" + " " + member[0].seatNumber.toString())
            })

        //Fetches member photo from "https://avoindata.eduskunta.fi" and puts that photo
        //to image view
        model.loadOneMember(position).observe(
            viewLifecycleOwner,
            { member ->
                Glide.with(this)
                    .load("https://avoindata.eduskunta.fi/${member[0].picture}")
                    .into(binding.MemberPhoto)
            })

        //Button for scoring the member, clicking button once decreases
        //members score by 1
        //new score is inserted to the score database
        binding.minusButton.setOnClickListener {
            score--
            CoroutineScope(Dispatchers.IO).launch {
                val scoreInsert = Score(position, score)
                model.insertScore(scoreInsert)
            }
        }

        //Button for scoring the member, clicking button once adds 1 to member score
        //new score is inserted to the score database
        binding.plusButton.setOnClickListener {
            score++
            CoroutineScope(Dispatchers.IO).launch {
                val scoreInsert = Score(position, score)
                model.insertScore(scoreInsert)

            }
        }

        //Notes button which opens the fragment where user can
        //see and add notes for that member
        //Sends current member's member number to next fragment
        binding.button3.setOnClickListener(View.OnClickListener {
            val action = MemberFragmentDirections.actionMemberFragmentToMemberInfoFragment(position)
            view?.findNavController()?.navigate(action)
        })

        return binding.root

    }
}