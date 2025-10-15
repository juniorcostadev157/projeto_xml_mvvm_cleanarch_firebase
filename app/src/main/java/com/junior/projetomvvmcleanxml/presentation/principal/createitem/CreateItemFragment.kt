package com.junior.projetomvvmcleanxml.presentation.principal.createitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.junior.projetomvvmcleanxml.core.hideKeyboard
import com.junior.projetomvvmcleanxml.databinding.FragmentCreateItemBinding
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer


class CreateItemFragment : Fragment() {

    private var _binding: FragmentCreateItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CreateItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val factory = InjectContainer.createItemFactory
        viewModel =   ViewModelProvider(this, factory)[CreateItemViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner){state->
            when(state){
                is CreateItemUiState.Empty -> hideLoading()
                is CreateItemUiState.Loading-> showLoading()
                is CreateItemUiState.Success->{
                    hideLoading()
                    clearField()
                    showToast("Item criado com sucesso")

                }
                is CreateItemUiState.Error->{
                    hideLoading()
                    showToast(state.message)
                }
            }
        }
        createItem()

    }


    private fun createItem(){
        binding.btnCriar.setOnClickListener {
            hideKeyboard()
            val name = binding.etNameItem.text.toString()
            if (name.isBlank()){
                showToast("Preencha o nome do item")
            }else{
                viewModel.createItem(name)


            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(){
        binding.progressLoadingItem.visibility = View.VISIBLE
        binding.btnCriar.isEnabled = false
        binding.btnCriar.alpha = 0.6f
    }

    private fun hideLoading(){
        binding.progressLoadingItem.visibility = View.GONE
        binding.btnCriar.isEnabled = true
        binding.btnCriar.alpha = 1.0f
    }

    private fun clearField(){
        binding.etNameItem.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}