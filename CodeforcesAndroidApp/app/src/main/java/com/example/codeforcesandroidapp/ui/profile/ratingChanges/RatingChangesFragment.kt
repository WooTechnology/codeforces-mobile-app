package com.example.codeforcesandroidapp.ui.profile.ratingChanges

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
import com.example.codeforcesandroidapp.network.NetworkUtil
import com.example.codeforcesandroidapp.network.models.profile.RatingChangeMapper
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository_Impl


class RatingChangesFragment : Fragment() {

    private lateinit var ratingViewModel: RatingChangesViewModel
    private lateinit var ratingRepository: RatingChangesRepository
    private lateinit var ratingAdapter: RatingChangeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating_changes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        ratingAdapter = RatingChangeAdapter()
        recyclerView.adapter = ratingAdapter


        ratingRepository = RatingChangesRepository_Impl(apiService = NetworkUtil.createCodeforcesService(NetworkUtil.createRetrofitClient()),
            ratingChangesMapper = RatingChangeMapper()
        )

        val ratingViewModelFactory = RatingChangesViewModelFactory(ratingRepository)


        ratingViewModel = ViewModelProvider(this, ratingViewModelFactory).get(RatingChangesViewModel::class.java)
        ratingViewModel.fetchRating()
        ratingViewModel.ratingList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e("Rating List Fragment", it.toString())
                ratingAdapter.fillData(it)
            }
        })


    }
}