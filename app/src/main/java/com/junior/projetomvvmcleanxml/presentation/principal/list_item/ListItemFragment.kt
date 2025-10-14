package com.junior.projetomvvmcleanxml.presentation.principal.list_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.junior.projetomvvmcleanxml.databinding.FragmentListItemBinding
import com.junior.projetomvvmcleanxml.presentation.principal.adapter.AdapterItem
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer

class ListItemFragment : Fragment() {

    private var _binding: FragmentListItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListItemViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val factory = InjectContainer.listItemFactory
        viewModel = ViewModelProvider(this, factory)[ListItemViewModel::class.java]


        val recyclerItems = binding.recylerItems
        recyclerItems.layoutManager = LinearLayoutManager(requireContext())
        recyclerItems.setHasFixedSize(true)
        val adapter = AdapterItem(requireContext(), mutableListOf())
        recyclerItems.adapter =adapter


        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
            is ListItemUiState.Empty -> {}
            is ListItemUiState.Loading->{}
            is ListItemUiState.Success->{
            adapter.updateList(state.items)
            }
            is ListItemUiState.Error->{

             }
        }

        }
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}