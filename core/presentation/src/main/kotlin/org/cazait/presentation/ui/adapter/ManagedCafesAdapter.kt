package org.cazait.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.bmsk.domain.model.ManagedCafe
import org.cazait.presentation.databinding.ItemManagedCafeBinding
import org.cazait.presentation.ui.viewholder.ManagedCafesViewHolder

class ManagedCafesAdapter(
    private val onClick: (ManagedCafe) -> Unit
) : ListAdapter<ManagedCafe, ManagedCafesViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagedCafesViewHolder {
        return ManagedCafesViewHolder(
            binding = ItemManagedCafeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: ManagedCafesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ManagedCafe>() {
            override fun areItemsTheSame(oldItem: ManagedCafe, newItem: ManagedCafe): Boolean {
                return oldItem.cafeId == newItem.cafeId
            }

            override fun areContentsTheSame(oldItem: ManagedCafe, newItem: ManagedCafe): Boolean {
                return oldItem == newItem
            }
        }
    }
}