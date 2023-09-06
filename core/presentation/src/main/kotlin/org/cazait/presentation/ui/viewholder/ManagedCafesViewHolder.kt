package org.cazait.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.bmsk.domain.model.ManagedCafe
import org.cazait.presentation.R
import org.cazait.presentation.databinding.ItemManagedCafeBinding

class ManagedCafesViewHolder(
    private val binding: ItemManagedCafeBinding,
    private val onClick: (ManagedCafe) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ManagedCafe) {
        binding.item = item
        binding.cafePreviewImageView.load(item.cafeImages[0].url) {
            crossfade(true)
            error(R.drawable.default_image_cazait)
        }
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}