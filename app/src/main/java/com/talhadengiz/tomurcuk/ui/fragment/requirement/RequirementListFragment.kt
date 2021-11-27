package com.talhadengiz.tomurcuk.ui.fragment.requirement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.data.adapter.RequirementAdapter
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.databinding.FragmentRequirementListBinding
import com.talhadengiz.tomurcuk.utils.SharedPrefHelper
import com.talhadengiz.tomurcuk.viewModel.RequirementVM

class RequirementListFragment : Fragment() {

    private lateinit var binding: FragmentRequirementListBinding
    private var firestore: FirebaseFirestore? = null
    private lateinit var requirementAdapter: RequirementAdapter
    private val model: RequirementVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequirementListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initClickListener()
        initRequirement()
        observe()
    }

    private fun init() {
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initClickListener() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_requirementListFragment_to_addRequirementFragment)
        }

        binding.btnLogin.setOnClickListener {
            if (isLogin()) {
                findNavController().navigate(R.id.action_requirementListFragment_to_foundationFragment)
            } else {
                findNavController().navigate(R.id.action_requirementListFragment_to_loginFragment)
            }
        }
    }

    private fun initRequirement() {
        binding.rvRequirement.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observe() {
        model.requirements.observe(viewLifecycleOwner,{
            binding.rvRequirement.adapter = RequirementAdapter(it as ArrayList<Requirement>)
            requirementAdapter = RequirementAdapter(it)
        })
    }

    private fun isLogin(): Boolean {
        return SharedPrefHelper(requireContext()).isLogin()
    }
}