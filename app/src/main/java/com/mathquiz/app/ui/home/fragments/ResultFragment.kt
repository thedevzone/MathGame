package com.mathquiz.app.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mathquiz.app.base.BaseFragment
import com.mathquiz.app.databinding.ResultFragmentBinding
import com.mathquiz.app.model.QuizResult
import com.mathquiz.app.ui.home.viewmodel.ResultViewModel
import com.mathquiz.app.utils.Constants
import com.mathquiz.app.utils.setSafeOnClickListener


class ResultFragment : BaseFragment() {

    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResultFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val list = it.getParcelableArrayList<QuizResult>(Constants.RESULTS) ?: arrayListOf()
            viewModel.fillResults(list)
        }
        binding.buttonDone.setSafeOnClickListener {
            goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}