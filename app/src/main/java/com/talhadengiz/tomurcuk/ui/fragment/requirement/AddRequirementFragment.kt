package com.talhadengiz.tomurcuk.ui.fragment.requirement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.databinding.FragmentAddRequirementBinding
import com.talhadengiz.tomurcuk.viewModel.RequirementVM

class AddRequirementFragment : Fragment() {

    private lateinit var binding: FragmentAddRequirementBinding
    private val model: RequirementVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRequirementBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        observe()
    }

    private fun initClickListener() {
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val total = binding.etTotal.text.toString()
            val location = binding.etLocation.text.toString()
            model.saveRequirement(title, total, location)
        }
    }

    private fun observe() {
        model.isSuccess.observe(viewLifecycleOwner, {
            if (it) findNavController().navigate(R.id.action_addRequirementFragment_to_requirementListFragment)
        })
    }
}