package com.example.codeforcesandroidapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.codeforcesandroidapp.R


class ProfileFragment : Fragment() {

//    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.ratingChanges).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_ratingChangesFragment)
        }

//        navController = Navigation.findNavController(view)
//        view.findViewById<TextView>(R.id.ratingChanges).setOnClickListener(this)

    }

//    override fun onClick(p0: View?) {
//        when(p0!!.id){
//            R.id.ratingChanges -> {
//                navController!!.navigate(R.id.action_profileFragment_to_ratingChangesFragment)
//            }
//        }
//    }


}