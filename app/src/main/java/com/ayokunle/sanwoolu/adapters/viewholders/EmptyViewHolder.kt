package com.ayokunle.sanwoolu.adapters.viewholders

import android.animation.AnimatorInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayokunle.sanwoolu.R
import com.ayokunle.sanwoolu.databinding.LayoutNoUserBinding

class EmptyViewHolder(
    private val binding: LayoutNoUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        val imageAnimator =
            AnimatorInflater.loadAnimator(binding.root.context, R.animator.anim_move_up)
        imageAnimator.setTarget(binding.imgNoUser)
        imageAnimator.start()
    }

    companion object {
        @JvmStatic
        fun new(
            inflater: LayoutInflater,
            parent: ViewGroup?
        ): EmptyViewHolder {
            val binding = LayoutNoUserBinding.inflate(inflater, parent, false)
            return EmptyViewHolder(binding)
        }
    }
}