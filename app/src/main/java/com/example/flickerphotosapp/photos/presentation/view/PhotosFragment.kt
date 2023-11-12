package com.example.flickerphotosapp.photos.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickerphotosapp.databinding.FragmentPhotosBinding
import com.example.flickerphotosapp.photos.presentation.adapter.PhotosAdapter
import com.example.flickerphotosapp.photos.presentation.stateholder.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: PhotosViewModel by viewModels()
    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter {
            val action: NavDirections = PhotosFragmentDirections.actionToFullPhotoFragment(it.imageUrl)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
        viewModel.getPhotos("Color", 0)
    }

    private fun initViews() = with(binding) {
        rvPhotos.adapter = adapter
        rvPhotos.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.photos.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

}