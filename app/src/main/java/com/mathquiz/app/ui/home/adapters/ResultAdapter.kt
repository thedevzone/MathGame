package com.mathquiz.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mathquiz.app.databinding.RowResultBinding
import com.mathquiz.app.model.QuizResult

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    private var results: List<QuizResult> = listOf()

    fun updateResults(list: List<QuizResult>) {
        results = list
        notifyDataSetChanged()
    }

    inner class ResultViewHolder(private val binding: RowResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: QuizResult) {
            binding.result = result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            RowResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size
}