package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.GridLayoutManager
import androidx.core.os.bundleOf
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ProductsAdapter(object : ProductClickListener {
            override fun onProductClick(product: Product) {
                val bundle = bundleOf(
                    "title" to product.title,
                    "price" to product.price,
                    "sold" to product.sold.toInt(),
                    "description" to product.description,
                    "rating" to product.rating,
                    "imageResource" to product.imageResource
                )

                val productDetailsFragment = ProductDetailsFragment().apply {
                    arguments = bundle
                }

                findNavController(R.id.fragment_container).navigate(
                    R.id.action_main_to_product_details,
                    bundle
                )
            }
        })

        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter

        val productList: List<Product> = listOf(
            Product(
                R.drawable.beautiful_white_vase,
                "Beautiful White Vase",
                "4.2",
                "This is one of the most beautiful white vases",
                "$60.00",
                "1299 Sold"
            ),
            Product(
                R.drawable.black_decorative_vase,
                "Black Decorative Vase",
                "3.7",
                "Made in China",
                "$45.00",
                "420 Sold"
            )
        )

        adapter.submitList(productList)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val filterText = s.toString().trim()
                val filteredList =
                    productList.filter { it.title.contains(filterText, ignoreCase = true) }
                adapter.submitFilteredList(filteredList)
            }
        })
    }
}
