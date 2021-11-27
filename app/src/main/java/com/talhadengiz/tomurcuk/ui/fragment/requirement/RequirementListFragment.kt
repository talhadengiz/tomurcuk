package com.talhadengiz.tomurcuk.ui.fragment.requirement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.data.adapter.RequirementAdapter
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.databinding.FragmentRequirementListBinding

class RequirementListFragment : Fragment() {

    private lateinit var binding: FragmentRequirementListBinding
    private var firestore: FirebaseFirestore? = null

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
        bindRequirementList()
    }

    private fun init() {
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initRequirement() {
        binding.rvRequirement.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initClickListener() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_requirementListFragment_to_addRequirementFragment)
        }
    }

    private fun bindRequirementList() {
        firestore?.collection("requirement")?.addSnapshotListener{value,error ->
            value?.toObjects(Requirement::class.java).let {
                binding.rvRequirement.adapter = RequirementAdapter(it as ArrayList<Requirement>)
            }
        }
    }
}