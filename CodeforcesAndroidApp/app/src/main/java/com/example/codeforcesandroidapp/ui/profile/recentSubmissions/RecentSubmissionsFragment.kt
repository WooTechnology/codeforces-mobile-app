package com.example.codeforcesandroidapp.ui.profile.recentSubmissions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.adapter.RecentSubmissionsAdapter
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.RecentSubmissionsMapper
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository_Impl
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository_Impl
import com.example.codeforcesandroidapp.ui.profile.ratingChanges.RatingChangesViewModel
import com.example.codeforcesandroidapp.ui.profile.ratingChanges.RatingChangesViewModelFactory
import com.example.codeforcesandroidapp.utils.Constants.PAGE_SIZE

class RecentSubmissionsFragment : Fragment() {

    private lateinit var submissionsRepo : RecentSubmissionsRepository
    private lateinit var submissionsViewModel : RecentSubmissionsViewModel
    private lateinit var submissionsAdapter : RecentSubmissionsAdapter
    private var isLoading : Boolean = false
    private var isLastPage : Boolean = false
    private var isScrolling : Boolean = false
    private lateinit var layoutManager: LinearLayoutManager


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
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        submissionsAdapter = RecentSubmissionsAdapter()
        recyclerView.adapter = submissionsAdapter

        //onScrollListener for RecyclerView for implementing pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                val isAtLastItem = firstVisibleItemPosition+visibleItemCount>=totalItemCount
                val isTotalMorethanVisible = totalItemCount>=PAGE_SIZE

                if(!isLoading && !isLastPage && isAtLastItem && firstVisibleItemPosition>=0 && isTotalMorethanVisible && isScrolling){
                    submissionsViewModel.fetchSubmissions()
                }
            }
        })


        //API call work starts
        submissionsRepo = RecentSubmissionsRepository_Impl(apiService = NetworkUtil.createCodeforcesService(
            NetworkUtil.createRetrofitClient()),
            recentSubmissionsMapper = RecentSubmissionsMapper()
        )

        val submissionsViewModelFactory = RecentSubmissionsViewModelFactory(submissionsRepo)


        submissionsViewModel = ViewModelProvider(this, submissionsViewModelFactory).get(RecentSubmissionsViewModel::class.java)
        submissionsViewModel.fetchSubmissions()

        //for observing the main submissions output
        submissionsViewModel.submissionsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e("SubmissionListFragment", it.toString())
                val filtered = it.asReversed()
                submissionsAdapter.fillData(filtered)

            }
        })

        //for observing whether to show the loading icon or not
        submissionsViewModel.isLoading.observe(viewLifecycleOwner, {
            if(it!=null){
                isLoading = it
                if(it){
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })

        //for observing whether this is the last page or not
        submissionsViewModel.isLastPage.observe(viewLifecycleOwner,{
            if(it!=null){
                isLastPage = it
            }
        })


    }
}