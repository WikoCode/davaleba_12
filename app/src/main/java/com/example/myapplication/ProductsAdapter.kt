package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemProductsBinding

class ProductsAdapter(private val clickListener: ProductClickListener) :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {


    class ProductViewHolder(
        private val binding: ItemProductsBinding,
        private val clickListener: ProductClickListener,
        private val productList: List<Product>
    ) : RecyclerView.ViewHolder(binding.root) {



        fun bind(product: Product) {
            binding.ivProduct.setImageResource(product.imageResource)
            binding.tvTitle.text = product.title
            binding.tvRating.text = product.rating
            binding.tvPrice.text = product.price
            binding.tvSold.text = product.sold

            binding.root.setOnClickListener {
                if (productList.isNotEmpty()) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        clickListener.onProductClick(productList[adapterPosition])


                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, clickListener, productList)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    private var productList = listOf<Product>()


    fun submitFilteredList(filteredList: List<Product>) {
        this.productList = filteredList
        submitList(filteredList)
    }

}
