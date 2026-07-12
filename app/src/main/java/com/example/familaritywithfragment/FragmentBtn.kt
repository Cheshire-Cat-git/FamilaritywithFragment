package com.example.familaritywithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.familaritywithfragment.databinding.FragmentBtnBinding

class FragmentBtn : Fragment() {

    private var _binding: FragmentBtnBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnColor.setOnClickListener {
            viewModel.changeColors()
        }
        
        binding.btnSwap.setOnClickListener {
            viewModel.swapFragments()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}