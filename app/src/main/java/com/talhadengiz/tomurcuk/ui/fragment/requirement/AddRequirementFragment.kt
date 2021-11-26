package com.talhadengiz.tomurcuk.ui.fragment.requirement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.databinding.FragmentAddRequirementBinding

class AddRequirementFragment : Fragment() {

    private lateinit var binding : FragmentAddRequirementBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRequirementBinding.inflate(layoutInflater)
        return binding.root
    }
}