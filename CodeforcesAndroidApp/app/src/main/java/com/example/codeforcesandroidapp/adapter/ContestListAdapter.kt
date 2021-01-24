package com.example.codeforcesandroidapp.adapter

import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.model.contests.ContestBusinessModel

class ContestListAdapter : RecyclerView.Adapter<ContestListAdapter.ContestListViewHolder>() {

    private val contestList =  ArrayList<ContestBusinessModel>()
    private lateinit var listener : ContestOnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contest,parent,false)
        return ContestListViewHolder(view,listener)

    }

    override fun getItemCount(): Int {
        return contestList.size
    }

    override fun onBindViewHolder(holder: ContestListViewHolder, position: Int) {

        holder.bindingView(contestList[position])
    }

    fun fillData(contestList : List<ContestBusinessModel> ){
        this.contestList.addAll(contestList)
        Log.e("ContestListAdapter", contestList.toString())
        notifyDataSetChanged()
    }

    fun setListener(listener:ContestOnClickListener){
        this.listener = listener
    }

    class ContestListViewHolder(itemView: View, private val listener: ContestOnClickListener) : RecyclerView.ViewHolder(itemView) {

        private var contestName : TextView = itemView.findViewById(R.id.contestName)
        private var startTime : TextView = itemView.findViewById(R.id.contestStartTime)
        private var duration : TextView = itemView.findViewById(R.id.contestDuration)

        fun bindingView(contest:ContestBusinessModel){

            contestName.text = contest.name
            startTime.text = SpannableStringBuilder().bold { append("Contest starts on: ") }.append(contest.startTimeSeconds.toString())
            duration.text = SpannableStringBuilder().bold { append("Contest Duration: ") }.append(contest.durationSeconds.toString())

            itemView.setOnClickListener{
                listener.onClickContest(contest)
            }

        }
    }

    interface ContestOnClickListener{
        fun onClickContest(contest : ContestBusinessModel)
    }
}



