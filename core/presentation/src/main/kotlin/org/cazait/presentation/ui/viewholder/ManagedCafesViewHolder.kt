package org.cazait.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.bmsk.domain.model.ManagedCafe
import org.cazait.presentation.databinding.ItemManagedCafeBinding

class ManagedCafesViewHolder(
    private val binding: ItemManagedCafeBinding,
    private val onClick: (ManagedCafe) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ManagedCafe) {
        binding.item = item
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}