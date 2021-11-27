package com.talhadengiz.tomurcuk.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.databinding.ItemRequirementBinding

class RequirementAdapter(private val requirementList: ArrayList<Requirement>) :
    RecyclerView.Adapter<RequirementAdapter.RequirementVH>() {

    inner class RequirementVH(val binding: ItemRequirementBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.cvItemRequirement.setOnClickListener {

                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequirementVH {
        return RequirementVH(
            ItemRequirementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RequirementVH, position: Int) {
        holder.binding.apply {
            tvTitle.text = requirementList[position].title
            tvTotal.text = requirementList[position].total
            tvLocation.text = requirementList[position].location
        }
    }

    override fun getItemCount(): Int = requirementList.size
}