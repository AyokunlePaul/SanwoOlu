package com.ayokunle.sanwoolu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayokunle.sanwoolu.adapters.viewholders.EmptyViewHolder
import com.ayokunle.sanwoolu.adapters.viewholders.UserViewHolder
import com.ayokunle.sanwoolu.models.UserEntity
import com.ayokunle.sanwoolu.utils.AutoUpdateRecyclerView
import com.ayokunle.sanwoolu.utils.image.ImageLoader
import kotlin.properties.Delegates

class UserAdapter(
    private val imageLoader: ImageLoader,
    private val onUserClick: (UserEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdateRecyclerView {

    init {
        setHasStableIds(true)
    }

    private var users: List<UserEntity> by Delegates.observable(emptyList()) { _, oldUsers, newUsers
        ->
        autoNotify(oldUsers, newUsers) { oldUser, newUser ->
            oldUser.hashCode() == newUser.hashCode()
        }
    }
    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        return if (viewType == TYPE_EMPTY) {
            EmptyViewHolder.new(inflater!!, parent)
        } else UserViewHolder.new(inflater!!, parent, imageLoader)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bind(users[position], onUserClick)
            is EmptyViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = if (users.isEmpty()) 1 else users.size

    override fun getItemViewType(position: Int): Int =
        if (users.isEmpty()) TYPE_EMPTY else TYPE_USER

    override fun getItemId(position: Int): Long =
        if (users.isEmpty()) 0L else users[position].hashCode().toLong()

    fun updateUsers(data: List<UserEntity>) {
        users = data
    }

    companion object {
        private const val TYPE_EMPTY = 0
        private const val TYPE_USER = 1
    }
}