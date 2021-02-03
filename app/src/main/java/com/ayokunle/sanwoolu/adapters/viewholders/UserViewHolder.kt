package com.ayokunle.sanwoolu.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayokunle.sanwoolu.R
import com.ayokunle.sanwoolu.databinding.LayoutUserBinding
import com.ayokunle.sanwoolu.models.UserEntity
import com.ayokunle.sanwoolu.utils.image.ImageLoader
import java.util.*

class UserViewHolder(
    private val binding: LayoutUserBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: UserEntity, onUserClick: (UserEntity) -> Unit) {
        with(binding) {
            imageLoader.loadImage(data.picture, imgUser)
            tvFullname.text = binding.root.context.getString(
                R.string.fullName,
                data.title.capitalize(Locale.getDefault()),
                data.firstName,
                data.lastName
            )
            tvEmail.text = data.email
            tvGender.text = data.gender.capitalize(Locale.getDefault())
            tvPhone.text = data.phone
            root.setOnClickListener { onUserClick.invoke(data) }
        }
    }

    companion object {
        @JvmStatic
        fun new(
            inflater: LayoutInflater,
            parent: ViewGroup?,
            imageLoader: ImageLoader
        ): UserViewHolder {
            val binding = LayoutUserBinding.inflate(inflater, parent, false)
            return UserViewHolder(binding, imageLoader)
        }
    }
}