package com.talhadengiz.tomurcuk.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.databinding.ItemRequirementBinding

class RequirementAdapter(private val requirementList: ArrayList<Requirement>) :
    RecyclerView.Adapter<RequirementAdapter.RequirementVH>() {

    inner class RequirementVH(val binding: ItemRequirementBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            tvTitle.text =
                (tvTitle.context).getString(R.string.requirement, requirementList[position].title)
            tvTotal.text =
                (tvTotal.context).getString(R.string.total, requirementList[position].total)
            tvLocation.text = (tvLocation.context).getString(
                R.string.location,
                requirementList[position].location
            )
        }
    }

    override fun getItemCount(): Int = requirementList.size

    fun getData(): MutableList<Requirement> {
        return requirementList
    }
}