package com.mathquiz.app.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.mathquiz.app.base.BaseFragment
import com.mathquiz.app.databinding.QuizFragmentBinding
import com.mathquiz.app.ui.home.MainActivity
import com.mathquiz.app.ui.home.viewmodel.QuizViewModel
import com.mathquiz.app.utils.Constants
import com.mathquiz.app.utils.DialogUtils


class QuizFragment : BaseFragment() {
    private var _binding: QuizFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    DialogUtils.showGeneralDialog(
                        requireContext(),
                        "Are you sure you want to go back?",
                        "Yes",
                        "No"
                    ) {
                        activity?.apply {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()
        timerStart()
    }

    private fun addObserver() {
        viewModel.quizFinishedObserver.observe(this) {
            val fragment = ResultFragment()
            fragment.arguments = bundleOf(Constants.RESULTS to viewModel.getResults())
            loadFragment(fragment)
        }

        viewModel.resetTimerObserver.observe(this) {
            cancelTimer()
            timerStart()
        }
    }

    private var countDownTimer: CountDownTimer? = null
    private fun timerStart(timeDifference: Long = 30000) {
        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(timeDifference, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    var seconds = (millisUntilFinished / 1000).toInt()
                    val hours = seconds / (60 * 60)
                    val tempMint = seconds - hours * 60 * 60
                    val minutes = tempMint / 60
                    seconds = tempMint - minutes * 60
                    try {
                        val sec = if (seconds > 9) "$seconds" else "0$seconds"
                        binding.textViewSecond.text = sec
                    } catch (e: Exception) {
                    }
                }

                override fun onFinish() {
                    try {
                        viewModel.setupNewQuestion()
                    } catch (e: Exception) {
                    }
                }
            }.start()
        }
    }

    private fun cancelTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        cancelTimer()
    }

}