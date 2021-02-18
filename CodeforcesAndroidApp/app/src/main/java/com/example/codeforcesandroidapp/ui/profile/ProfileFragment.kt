package com.example.codeforcesandroidapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.bold
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.model.profile.UserProfileBusinessModel
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.RecentSubmissionsMapper
import com.example.codeforcesandroidapp.network.models.profile.userProfile.UserProfileMapper
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository_Impl
import com.example.codeforcesandroidapp.repository.profile.UserProfileRepository
import com.example.codeforcesandroidapp.repository.profile.UserProfileRepository_Impl
import com.example.codeforcesandroidapp.ui.profile.recentSubmissions.RecentSubmissionsViewModel
import com.example.codeforcesandroidapp.ui.profile.recentSubmissions.RecentSubmissionsViewModelFactory
import com.example.codeforcesandroidapp.ui.profile.userProfile.UserProfileViewModel
import com.example.codeforcesandroidapp.ui.profile.userProfile.UserProfileViewModelFactory


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel : UserProfileViewModel
    private lateinit var profileRepo : UserProfileRepository
    private var userHandleName : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileRepo = UserProfileRepository_Impl(apiService = NetworkUtil.createCodeforcesService(
            NetworkUtil.createRetrofitClient()),
            userProfileMapper = UserProfileMapper() 
        )
        val profileViewModelFactory = UserProfileViewModelFactory(profileRepo)
        profileViewModel = ViewModelProvider(this, profileViewModelFactory).get(UserProfileViewModel::class.java)

        //handling shared preferences
        userHandleName = context?.getSharedPreferences("user_handle", Context.MODE_PRIVATE)?.getString("handle", null)

        if(userHandleName!=null){
            profileViewModel.fetchProfile(userHandleName!!)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //handling visibility
        if(userHandleName==null){
            view.findViewById<LinearLayout>(R.id.usernameCard).visibility = View.GONE
            view.findViewById<LinearLayout>(R.id.editUsernameCard).visibility = View.VISIBLE
        }else{
            view.findViewById<LinearLayout>(R.id.usernameCard).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.editUsernameCard).visibility = View.GONE
        }

        //updating the Profile card
        profileViewModel.userProfile.observe(viewLifecycleOwner, {
            if(it!=null){
                prepareUserCard(view, it[0])
            }
        })

        view.findViewById<CardView>(R.id.ratingChanges).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_ratingChangesFragment)
        }

        view.findViewById<CardView>(R.id.recentSubmissions).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_recentSubmissionsFragment)
        }

        //API call to get the profile details
        view.findViewById<Button>(R.id.submitProfile).setOnClickListener {

            val userHandle = view.findViewById<EditText>(R.id.editUsername).text.toString()
            if (userHandle.trim().isEmpty()) {
                return@setOnClickListener
            }

            profileViewModel.fetchProfile(userHandle)
        }

        //for changing the username once set
        view.findViewById<LinearLayout>(R.id.usernameCard).setOnClickListener {
            view.findViewById<LinearLayout>(R.id.usernameCard).visibility = View.GONE
            view.findViewById<LinearLayout>(R.id.editUsernameCard).visibility = View.VISIBLE

            profileViewModel.userProfile.observe(viewLifecycleOwner, {
                if(it!=null){
                    updateUserCard(view, it[0])
                }
            })

        }
    }

    private fun prepareUserCard(view:View, profile : UserProfileBusinessModel){

        view.findViewById<LinearLayout>(R.id.usernameCard).visibility = View.VISIBLE
        view.findViewById<LinearLayout>(R.id.editUsernameCard).visibility = View.GONE

        val userContributions : TextView = view.findViewById(R.id.profileContribution)
        val userRating : TextView = view.findViewById(R.id.profileRating)
        val userRank : TextView = view.findViewById(R.id.profileRank)
        val username : TextView = view.findViewById(R.id.profileUsername)


        username.text = SpannableStringBuilder().bold { append("Username : ") }.append(profile.handle)
        userContributions.text = SpannableStringBuilder().bold { append("Contribution : ") }.append(profile.contribution)
        userRating.text = SpannableStringBuilder().bold { append("Rating : ") }.append(profile.rating.toString())
        userRank.text = SpannableStringBuilder().bold { append("Rank : ") }.append(profile.rank)

        if (userHandleName == null) {
            userHandleName = profile.handle
            view.context.getSharedPreferences("user_handle", Context.MODE_PRIVATE).edit().putString("handle", profile.handle).apply()
        }
    }

    private fun updateUserCard(view:View, profile : UserProfileBusinessModel){


        val userContributions : TextView = view.findViewById(R.id.profileContribution)
        val userRating : TextView = view.findViewById(R.id.profileRating)
        val userRank : TextView = view.findViewById(R.id.profileRank)
        val username : TextView = view.findViewById(R.id.profileUsername)

        val usernameSP = context?.getSharedPreferences("user_profile",0)
        username.text = SpannableStringBuilder().bold { append("Username : ") }.append(profile.handle)
        userContributions.text = SpannableStringBuilder().bold { append("Contribution : ") }.append(profile.contribution)
        userRating.text = SpannableStringBuilder().bold { append("Rating : ") }.append(profile.rating.toString())
        userRank.text = SpannableStringBuilder().bold { append("Rank : ") }.append(profile.rank)

        //as username would already be set
        userHandleName = profile.handle
        view.context.getSharedPreferences("user_handle", Context.MODE_PRIVATE).edit().putString("handle", profile.handle).apply()

    }




}