package com.example.codeforcesandroidapp.adapter

import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforcesandroidapp.R
import com.example.codeforcesandroidapp.model.profile.RecentSubmissionsBusinessModel
import com.example.codeforcesandroidapp.utils.Constants
import org.w3c.dom.Text

class RecentSubmissionsAdapter: RecyclerView.Adapter<RecentSubmissionsAdapter.RecentSubmissionsViewHolder>() {

    private val recentSubmissionsList = ArrayList<RecentSubmissionsBusinessModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSubmissionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_submissions,parent,false)
        return RecentSubmissionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentSubmissionsViewHolder, position: Int) {
        holder.bindingView(recentSubmissionsList[position])
    }

    override fun getItemCount(): Int {
        return recentSubmissionsList.size
    }

    fun fillData(submissionsList : List<RecentSubmissionsBusinessModel>){
        this.recentSubmissionsList.addAll(submissionsList)
        Log.e("SubmissionsAdapter", recentSubmissionsList.toString())
        notifyDataSetChanged()
    }


    class RecentSubmissionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var problemName : TextView = itemView.findViewById(R.id.problemName)
        private var problemInfo : TextView = itemView.findViewById(R.id.problemInfo)
        private var solutionTime : TextView = itemView.findViewById(R.id.solutionTime)
        private var verdict : TextView = itemView.findViewById(R.id.verdict)

        fun bindingView(submissions: RecentSubmissionsBusinessModel){

            problemName.text = submissions.problem.name

            problemInfo.text = StringBuilder().append(submissions.passedTestCount).append(" cases passed, ").append(
                submissions.timeConsumedMillis).append(" ms, ").append(submissions.programmingLanguage).toString()

            solutionTime.text = Constants.convertEpochToStringDate(submissions.creationTimeSeconds)
            verdict.text = submissions.verdict

            if(submissions.verdict == "OK"){
                verdict.setTextColor(itemView.context.resources.getColor(R.color.passed))
            } else {
                verdict.setTextColor(itemView.context.resources.getColor(R.color.failed))
            }
        }

    }

}