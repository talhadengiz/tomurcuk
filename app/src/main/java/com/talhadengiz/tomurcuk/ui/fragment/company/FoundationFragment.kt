package com.talhadengiz.tomurcuk.ui.fragment.company

import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aydem.ui.fragments.energy_saving.list.SwipeController
import com.aydem.ui.fragments.energy_saving.list.SwipeControllerActions
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.data.adapter.RequirementAdapter
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.databinding.FragmentFoundationBinding

class FoundationFragment : Fragment() {

    private lateinit var binding: FragmentFoundationBinding
    private var firestore: FirebaseFirestore? = null
    private lateinit var requirementAdapter: RequirementAdapter

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
        bindRequirementList()
        setRequirementStatus()
    }

    private fun setRequirementStatus() {
        var swipeController: SwipeController? = null
        lateinit var viewHolder: RecyclerView.ViewHolder

        swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                val data = requirementAdapter?.getData()
                data!![position].status = true
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

    private fun init() {
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initRequirement() {
        binding.rvRequirement.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun bindRequirementList() {
        firestore?.collection("requirement")?.addSnapshotListener { value, error ->
            value?.toObjects(Requirement::class.java).let {
                binding.rvRequirement.adapter = RequirementAdapter(it as ArrayList<Requirement>)
                requirementAdapter = RequirementAdapter(it)
            }
        }
    }
}