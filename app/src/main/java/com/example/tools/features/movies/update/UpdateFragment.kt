package com.example.tools.features.movies.update


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.tools.MyApplication
import com.example.tools.R
import com.example.tools.databinding.FragmentUpdateBinding
import com.example.tools.di.ViewModelFactory
import com.example.tools.features.movies.MovieViewModel
import javax.inject.Inject

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MovieViewModel by navGraphViewModels(R.id.movies_graph) { viewModelFactory }

    private val args: UpdateFragmentArgs by navArgs()

    override fun onAttach(context: Context) {

        (context.applicationContext as MyApplication).appComponent
            .movieComponent()
            .create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.movieEditing.postValue(args.movie)

        return  binding.root
    }
}
