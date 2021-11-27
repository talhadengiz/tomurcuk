package com.talhadengiz.tomurcuk.ui.fragment.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) { }

            override fun onFinish() {
                findNavController().navigate(R.id.action_splashFragment_to_requirementListFragment)
            }
        }.start()
    }
}