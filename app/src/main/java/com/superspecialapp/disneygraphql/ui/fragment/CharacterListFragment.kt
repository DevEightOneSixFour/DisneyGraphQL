package com.superspecialapp.disneygraphql.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.superspecialapp.disneygraphql.data.model.DisneyItem
import com.superspecialapp.disneygraphql.databinding.FragmentListOfCharactersBinding
import com.superspecialapp.disneygraphql.presentation.DisneyViewModel
import com.superspecialapp.disneygraphql.ui.UIState
import com.superspecialapp.disneygraphql.ui.adapter.CharacterAdapter

class CharacterListFragment: Fragment() {

    private var _binding: FragmentListOfCharactersBinding? = null
    private val binding: FragmentListOfCharactersBinding get() = _binding!!

    private val viewModel: DisneyViewModel by activityViewModels()
    private val characterAdapter: CharacterAdapter by lazy { CharacterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfCharactersBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.Loading -> {
                    binding.apply {
                        pbLoading.visibility = View.VISIBLE
                        tvErrorText.visibility = View.GONE
                        rvCharacters.visibility = View.GONE
                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvErrorText.apply {
                            text = state.error.message
                            visibility = View.VISIBLE
                        }
                        rvCharacters.visibility = View.GONE
                    }
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvErrorText.visibility = View.GONE
                        rvCharacters.apply {
                            characterAdapter.setDisneyItemList(state.data as List<DisneyItem>)
                            adapter = characterAdapter
                            visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}