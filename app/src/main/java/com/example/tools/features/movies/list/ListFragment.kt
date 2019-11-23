package com.example.tools.features.movies.list


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tools.MyApplication
import com.example.tools.R
import com.example.tools.databinding.FragmentListBinding
import com.example.tools.di.ViewModelFactory
import com.example.tools.features.movies.MovieViewModel
import javax.inject.Inject


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val movieAdapter = MovieAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MovieViewModel by navGraphViewModels(R.id.movies_graph) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).appComponent
            .movieComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val linearLayoutManager = LinearLayoutManager(activity)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.adapter = movieAdapter
        binding.layoutManager = linearLayoutManager
        binding.dividerItemDecoration = DividerItemDecoration(activity,linearLayoutManager.orientation )

        return binding.root
    }

}
