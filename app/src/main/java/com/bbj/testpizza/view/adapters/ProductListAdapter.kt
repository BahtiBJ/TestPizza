package com.bbj.testpizza.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbj.testpizza.R
import com.bbj.testpizza.domain.models.ProductPreview
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton

class ProductListAdapter(context: Context, private val onProductClick: OnProductClick) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    interface OnProductClick {
        fun clickOnProduct(position: Int)
    }

    private val inflater = LayoutInflater.from(context)

    private var productList = arrayListOf<ProductPreview>()

    fun setList(list: List<ProductPreview>) {
        clearList()
        productList = list as ArrayList<ProductPreview>
        notifyItemRangeInserted(0,list.size)
    }

    private fun clearList(){
        val oldSize = productList.size
        productList.clear()
        notifyItemRangeRemoved(0,oldSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_product_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val productImage = view.findViewById<ImageView>(R.id.item_product_image)
        private val productName = view.findViewById<TextView>(R.id.item_product_name)
        private val productDescribtion = view.findViewById<TextView>(R.id.item_product_describtion)
        private val productPrice = view.findViewById<MaterialButton>(R.id.item_product_price)

        fun bind(position: Int) {
            val product = productList[position]

            val path = product.posterPath
            Glide.with(itemView.context)
                .load(path)
                .error(R.color.white_dark)
                .fitCenter()
                .into(productImage)

            productName.text = product.name
            productDescribtion.text = product.describtion
            productPrice.text = itemView.resources.getString(R.string.price, product.price)

            itemView.setOnClickListener {
                onProductClick.clickOnProduct(position)
            }
        }
    }
}