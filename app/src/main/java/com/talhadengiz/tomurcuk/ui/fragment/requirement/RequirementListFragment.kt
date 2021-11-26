package com.talhadengiz.tomurcuk.ui.fragment.requirement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.databinding.FragmentRequirementListBinding

class RequirementListFragment : Fragment() {

    private lateinit var binding : FragmentRequirementListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequirementListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        //TODO
    }
}