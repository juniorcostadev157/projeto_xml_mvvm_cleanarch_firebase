package com.junior.projetomvvmcleanxml.presentation.principal.list_item_room_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.junior.projetomvvmcleanxml.databinding.FragmentListRoomBinding
import com.junior.projetomvvmcleanxml.presentation.principal.adapter.AdapterItem
import com.junior.projetomvvmcleanxml.presentation.principal.list_item_firebase_fragment.ListItemUiState
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer


class ListRoomFragment : Fragment() {

    private var _binding: FragmentListRoomBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListRoomViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val factory = InjectContainer.listItemRoomFactory
        viewModel = ViewModelProvider(this, factory)[ListRoomViewModel::class.java]



        val recyclerItems = binding.recylerItemsLocal
        recyclerItems.layoutManager = LinearLayoutManager(requireContext())
        recyclerItems.setHasFixedSize(true)
        val adapter = AdapterItem(requireContext(), mutableListOf(), false)
        recyclerItems.adapter =adapter


        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListItemUiState.Loading -> showLoading()
                is ListItemUiState.Success -> {
                    hideLoading()
                    if (state.items.isEmpty()){
                        adapter.updateList(emptyList())


                    }else{
                        adapter.updateList(state.items)

                    }

                }
                is ListItemUiState.Empty -> {
                    hideLoading()
                    binding.recylerItemsLocal.visibility = View.GONE
                    binding.txtNoItems.visibility = View.VISIBLE
                }
                is ListItemUiState.Error -> hideLoading()
            }
        }
            }

    private fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE

    }

    private fun hideLoading() {
        binding.progressLoading.visibility = View.GONE

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}