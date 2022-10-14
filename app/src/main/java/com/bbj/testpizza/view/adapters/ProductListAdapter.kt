package com.bbj.testpizza.view.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbj.testpizza.R
import com.bbj.testpizza.domain.models.ProductPreview
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

class ProductListAdapter(context: Context, private val onProductClick: OnProductClick) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    interface OnProductClick {
        fun clickOnProduct(position: Int)
    }

    private val inflater = LayoutInflater.from(context)

    private var productList = arrayListOf<ProductPreview>()

    fun setList(list: List<ProductPreview>) {
        // TODO - Add DiffUtil
        productList = list as ArrayList<ProductPreview>
        notifyDataSetChanged()
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
            if (path.startsWith("drawable")){
                Picasso.get()
                    .load(Uri.parse(path))
                    .placeholder(R.color.white)
                    .error(R.color.white)
                    .fit()
                    .into(productImage)
            } else {
                Picasso.get()
                    .load(path)
                    .placeholder(R.color.white)
                    .error(R.color.white)
                    .fit()
                    .into(productImage)
            }

            productName.text = product.name
            productDescribtion.text = product.describtion
            productPrice.text = itemView.resources.getString(R.string.price, product.price)

            itemView.setOnClickListener {
                onProductClick.clickOnProduct(position)
            }
        }
    }
}