package com.example.codeforcesandroidapp.ui.contests

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.adapter.ContestListAdapter
import com.example.codeforcesandroidapp.model.contests.ContestBusinessModel
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.contests.ContestMapper
import com.example.codeforcesandroidapp.repository.contests.ContestRepository_Impl
import com.example.codeforcesandroidapp.repository.contests.ContestsRepository

class UpcomingContestFragment : Fragment(),ContestListAdapter.ContestOnClickListener {

    private lateinit var contestViewModel: ContestViewModel
    private lateinit var contestRepository: ContestsRepository
    private lateinit var contestAdapter: ContestListAdapter
    private var isLoading : Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_contest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //connected recyclerview to adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar2)


        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        contestAdapter = ContestListAdapter()
        recyclerView.adapter = contestAdapter
        contestAdapter.setListener(this)


        //calling API and initialisation of the Repository
        contestRepository = ContestRepository_Impl(
            NetworkUtil.createCodeforcesService(NetworkUtil.createRetrofitClient()),
            ContestMapper()
        )

        val contestViewModelFactory = ContestViewModelFactory(contestRepository)


        contestViewModel =
            ViewModelProvider(this, contestViewModelFactory).get(ContestViewModel::class.java)
        contestViewModel.fetchContests()
        contestViewModel.contestList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e("Contest List Fragment", it.toString())

                val filtered = it.filter { it.phase == "BEFORE" }
                val reversedList = filtered.asReversed()
                contestAdapter.fillData(reversedList)
            }
        })

        //loading icon handled here
        contestViewModel.isLoading.observe(viewLifecycleOwner, {
            if(it!=null){
                isLoading = it
                if(it){
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })



    }

    override fun onClickContest(contest:ContestBusinessModel) {
        Toast.makeText(context,contest.toString(),Toast.LENGTH_SHORT).show()
    }

}