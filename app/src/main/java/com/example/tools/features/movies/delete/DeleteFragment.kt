package com.example.tools.features.movies.delete

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.tools.MyApplication
import com.example.tools.R
import com.example.tools.di.ViewModelFactory
import com.example.tools.features.movies.MovieViewModel
import javax.inject.Inject

class DeleteFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MovieViewModel by navGraphViewModels(R.id.movies_graph) { viewModelFactory }

    private val args : DeleteFragmentArgs by navArgs()

    override fun onAttach(context: Context) {

        (context.applicationContext as MyApplication).appComponent
            .movieComponent()
            .create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(activity!!)
            .setIcon(R.drawable.ic_launcher_background)
            .setTitle("Deletar Filme")
            .setPositiveButton("Confirmar"
            ) { _, _ ->
                viewModel.deleteMovie(args.movieId)
            }
            .setNegativeButton("Cancelar"
            ) { _, _ ->
                dismiss()
            }
            .create()
    }
}
