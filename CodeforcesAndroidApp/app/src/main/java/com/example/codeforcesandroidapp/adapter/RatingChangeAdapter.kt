package com.example.codeforcesandroidapp.adapter

import android.os.Build
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.model.profile.RatingChangeBusinessModel
import com.example.codeforcesandroidapp.utils.Constants

class RatingChangeAdapter : RecyclerView.Adapter<RatingChangeAdapter.RatingChangeViewHolder>() {

    private val ratingChangeList = ArrayList<RatingChangeBusinessModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingChangeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rating_change,parent,false)
        return RatingChangeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingChangeViewHolder, position: Int) {
        holder.bindingView(ratingChangeList[position])
    }

    override fun getItemCount(): Int {
        return ratingChangeList.size
    }

    fun fillData(ratingList : List<RatingChangeBusinessModel> ){
        this.ratingChangeList.addAll(ratingList)
        Log.e("RatingChangeAdapter", ratingChangeList.toString())
        notifyDataSetChanged()
    }



    class RatingChangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var contestName : TextView = itemView.findViewById(R.id.contestName)
        private var contestRank : TextView = itemView.findViewById(R.id.contestRank)
        private var rankUpdate : TextView = itemView.findViewById(R.id.rankUpdated)
        private var ratingChange : TextView = itemView.findViewById(R.id.ratingChange)

        fun bindingView(rating : RatingChangeBusinessModel){

            contestName.text = rating.contestName
            contestRank.text = SpannableStringBuilder().bold{ append("Rank: ")}.append(rating.rank.toString())
            rankUpdate.text = SpannableStringBuilder().bold { append("Updated on: ") }.append(Constants.convertEpochToStringDate(rating.ratingUpdateTimeSeconds))
            

            if(rating.newRating-rating.oldRating>0){
                ratingChange.text = "+${rating.newRating-rating.oldRating}"
                ratingChange.setTextColor(itemView.context.resources.getColor(R.color.passed))
            }else{
                ratingChange.text = "-${rating.oldRating-rating.newRating}"
                ratingChange.setTextColor(itemView.context.resources.getColor(R.color.failed))
            }

        }


    }


}

