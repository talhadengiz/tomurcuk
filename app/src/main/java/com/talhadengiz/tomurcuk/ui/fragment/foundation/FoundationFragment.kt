package com.talhadengiz.tomurcuk.ui.fragment.foundation

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.tomurcuk.utils.SwipeControllerActions
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.data.adapter.RequirementAdapter
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.databinding.FragmentFoundationBinding
import com.talhadengiz.tomurcuk.utils.SwipeController
import com.talhadengiz.tomurcuk.utils.SharedPrefHelper
import com.talhadengiz.tomurcuk.viewModel.FoundationVM

class FoundationFragment : Fragment() {

    private lateinit var binding: FragmentFoundationBinding
    private var firestore: FirebaseFirestore? = null
    private lateinit var requirementAdapter: RequirementAdapter
    private val model: FoundationVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoundationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRequirement()
        initClickListener()
        observe()
        setRequirementStatus()
    }

    private fun init() {
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initRequirement() {
        binding.rvRequirement.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initClickListener() {
        binding.btnLogout.setOnClickListener {
            SharedPrefHelper(requireContext()).saveLogin(false)
            findNavController().navigate(R.id.action_foundationFragment_to_requirementListFragment)
        }
    }

    private fun observe() {
        model.requirements.observe(viewLifecycleOwner, Observer {
            binding.rvRequirement.adapter = RequirementAdapter(it as ArrayList<Requirement>)
            requirementAdapter = RequirementAdapter(it)
        })
    }

    private fun setRequirementStatus() {
        var swipeController: SwipeController? = null

        swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                val data = requirementAdapter.getData()
                model.update(data[position].title)
            }
        })
        val itemTouchhelper = ItemTouchHelper(swipeController)
        itemTouchhelper.attachToRecyclerView(binding.rvRequirement)

        binding.rvRequirement.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })
    }
}