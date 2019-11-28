package com.example.tools.features.movies.create


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.tools.MyApplication

import com.example.tools.R
import com.example.tools.databinding.FragmentCreateBinding
import com.example.tools.di.ViewModelFactory
import com.example.tools.features.movies.MovieLocalViewModel
import com.example.tools.features.movies.MovieViewModel
import com.example.tools.features.movies.update.UpdateFragmentArgs
import javax.inject.Inject

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

//    private val viewModel: MovieViewModel by navGraphViewModels(R.id.movies_graph) { viewModelFactory }
    private val viewModel: MovieLocalViewModel by navGraphViewModels(R.id.movies_graph) { viewModelFactory }

    override fun onAttach(context: Context) {

        (context.applicationContext as MyApplication).appComponent
            .movieComponent()
            .create(context.applicationContext)
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}
