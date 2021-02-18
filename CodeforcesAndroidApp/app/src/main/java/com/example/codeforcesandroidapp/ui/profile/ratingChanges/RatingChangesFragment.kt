package com.example.codeforcesandroidapp.ui.profile.ratingChanges

import android.content.Context
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
import com.example.codeforcesandroidapp.adapter.RatingChangeAdapter
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.profile.ratingChanges.RatingChangeMapper
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository_Impl
import com.example.codeforcesandroidapp.utils.Constants.PAGE_SIZE


class RatingChangesFragment : Fragment() {

    private lateinit var ratingViewModel: RatingChangesViewModel
    private lateinit var ratingRepository: RatingChangesRepository
    private lateinit var ratingAdapter: RatingChangeAdapter
    private var handle : String? = null
    private var isLoading : Boolean = false
    private var isLastPage : Boolean = false
    private var isScrolling : Boolean = false
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        handle = context?.getSharedPreferences("user_handle", Context.MODE_PRIVATE)?.getString("handle", null)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating_changes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar3)

        layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        ratingAdapter = RatingChangeAdapter()
        recyclerView.adapter = ratingAdapter

        //Pagination with Recycler View
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                val isNotLoadingAndNotLastPage = !isLastPage && !isLoading
                val isAtLastItem = firstVisibleItemPosition+visibleItemCount>=totalItemCount
                val isNotAtBeginning = firstVisibleItemPosition>=0
                val isTotalMoreThanVisible = totalItemCount>=PAGE_SIZE

                if(isNotLoadingAndNotLastPage && isNotAtBeginning && isAtLastItem && isTotalMoreThanVisible && isScrolling){
                    if(handle!=null){
                        ratingViewModel.fetchRating(handle!!)
                    }

                }
            }
        })



        //API call work starts
        ratingRepository = RatingChangesRepository_Impl(apiService = NetworkUtil.createCodeforcesService(NetworkUtil.createRetrofitClient()),
            ratingChangesMapper = RatingChangeMapper()
        )

        val ratingViewModelFactory = RatingChangesViewModelFactory(ratingRepository)


        ratingViewModel = ViewModelProvider(this, ratingViewModelFactory).get(RatingChangesViewModel::class.java)
        if(handle!=null){
            ratingViewModel.fetchRating(handle!!)
        }


        //observer to show the output
        ratingViewModel.ratingList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e("Rating List Fragment", it.toString())
                val filtered = it.asReversed()
                ratingAdapter.fillData(filtered)
            }
        })

        //observer to check whether the page is loafing
        ratingViewModel.isLoading.observe(viewLifecycleOwner,{
            if(it!=null){
                isLoading = it
                if(it){
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })

        //observer to check whether the page is last page
        ratingViewModel.isLastPage.observe(viewLifecycleOwner,{
            if(it!=null){
                isLastPage = it
            }
        })


    }
}