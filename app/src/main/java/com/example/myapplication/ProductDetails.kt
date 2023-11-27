package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val title = bundle.getString("title", "")
            val price = bundle.getString("price", "")
            val sold = bundle.getInt("sold", 0)
            val description = bundle.getString("description", "")
            val rating = bundle.getString("rating", "")
            val imageResource = bundle.getInt("imageResource", 0)

            binding.tvFragmentTitle.text = title
            binding.tvFragmentPrice.text = price.toString()
            binding.tvFragmentSold.text = "$sold"
            binding.tvDescription.text = description.toString()
            binding.tvFragmentRating.text = rating
            binding.ivProduct.setImageResource(imageResource)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
