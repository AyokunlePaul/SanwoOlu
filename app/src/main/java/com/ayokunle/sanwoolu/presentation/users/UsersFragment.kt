package com.ayokunle.sanwoolu.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayokunle.sanwoolu.R
import com.ayokunle.sanwoolu.adapters.UserAdapter
import com.ayokunle.sanwoolu.databinding.FragmentUsersBinding
import com.ayokunle.sanwoolu.models.UserEntity
import com.ayokunle.sanwoolu.utils.image.ImageLoader
import com.ayokunle.sanwoolu.utils.itemdecoration.RecyclerInsetsDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : Fragment(), (UserEntity) -> Unit {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding by lazy { _binding!! }

    private val viewModel: UsersFragmentViewModel by viewModels()

    @Inject
    lateinit var loader: ImageLoader

    private val userAdapter by lazy { UserAdapter(loader, this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        lifecycleScope.launchWhenStarted {
            with(viewModel) {
                userResponse.collect {
                    when (it) {
                        is UsersFragmentViewModel.GetUserResponse.Initial -> {
                        }
                        is UsersFragmentViewModel.GetUserResponse.Loading,
                        UsersFragmentViewModel.GetUserResponse.LoadingMore -> {
                        }
                        is UsersFragmentViewModel.GetUserResponse.Successful -> {
                            userAdapter.updateUsers(it.data)
                        }
                        is UsersFragmentViewModel.GetUserResponse.Failed -> {
                            Timber.tag("User Fragment").e(it.message)
                        }
                    }
                }
            }
        }
        viewModel.currentPage = 0
    }

    private fun setupViews() {
        with(binding.root) {
            adapter = userAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun supportsPredictiveItemAnimations(): Boolean = false
            }
            if (itemDecorationCount == 0) {
                val inset =
                    requireContext().resources.getDimension(R.dimen.user_recycler_inset).toInt()
                addItemDecoration(RecyclerInsetsDecoration(inset, inset))
            }
        }
    }

    override fun invoke(user: UserEntity) {
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}