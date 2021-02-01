package com.example.codeforcesandroidapp.ui.profile.recentSubmissions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.adapter.RatingChangeAdapter
import com.example.codeforcesandroidapp.adapter.RecentSubmissionsAdapter
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.profile.ratingChanges.RatingChangeMapper
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.RecentSubmissionsMapper
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository_Impl
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository_Impl
import com.example.codeforcesandroidapp.ui.profile.ratingChanges.RatingChangesViewModel
import com.example.codeforcesandroidapp.ui.profile.ratingChanges.RatingChangesViewModelFactory

class RecentSubmissionsFragment : Fragment() {

    private lateinit var submissionsRepo : RecentSubmissionsRepository
    private lateinit var submissionsViewModel : RecentSubmissionsViewModel
    private lateinit var submissionsAdapter : RecentSubmissionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_submissions, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        submissionsAdapter = RecentSubmissionsAdapter()
        recyclerView.adapter = submissionsAdapter


        //API call work starts
        submissionsRepo = RecentSubmissionsRepository_Impl(apiService = NetworkUtil.createCodeforcesService(
            NetworkUtil.createRetrofitClient()),
            recentSubmissionsMapper = RecentSubmissionsMapper()
        )

        val submissionsViewModelFactory = RecentSubmissionsViewModelFactory(submissionsRepo)


        submissionsViewModel = ViewModelProvider(this, submissionsViewModelFactory).get(RecentSubmissionsViewModel::class.java)
        submissionsViewModel.fetchSubmissions()
        submissionsViewModel.submissionsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e("SubmissionListFragment", it.toString())
                val filtered = it.asReversed()
                submissionsAdapter.fillData(filtered)
            }
        })


    }
}