package com.example.codeforcesandroidapp.ui.contest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.contest.ContestMapper
import com.example.codeforcesandroidapp.repo.contest.ContestRepository
import com.example.codeforcesandroidapp.repo.contest.ContestRepository_Impl

class UpcomingContestFragment : Fragment() {

    private lateinit var contestViewModel: ContestViewModel
    private lateinit var contestRepository: ContestRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_contest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contestRepository = ContestRepository_Impl(
            NetworkUtil.createCodeforcesService(NetworkUtil.createRetrofitClient()),
            ContestMapper()
        )
        val contestViewModelFactory = ContestViewModelFactory(contestRepository)
        contestViewModel =
            ViewModelProvider(this, contestViewModelFactory).get(ContestViewModel::class.java)
        contestViewModel.fetchContest()
        contestViewModel.contestList.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Log.e("ContestList", it.toString())
            }
        })
    }
}