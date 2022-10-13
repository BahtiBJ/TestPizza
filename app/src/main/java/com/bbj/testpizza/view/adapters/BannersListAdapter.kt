package com.bbj.testpizza.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbj.testpizza.R
import com.bbj.testpizza.domain.models.BannerModel
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class BannersListAdapter(context: Context, private val onBannerClick: OnBannerClick) :
    RecyclerView.Adapter<BannersListAdapter.ViewHolder>() {

    interface OnBannerClick {
        fun clickBanner(position: Int)
    }

    private val inflater = LayoutInflater.from(context)

    private var bannersList = arrayListOf<BannerModel>()

    fun setList(list: List<BannerModel>) {
        // TODO - Add DiffUtil
        bannersList = list as ArrayList<BannerModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_banner_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return bannersList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.findViewById<ShapeableImageView>(R.id.item_banner_image)

        fun bind(position: Int) {
            Picasso.get()
                .load(bannersList[position].imagePath)
                .placeholder(R.color.white)
                .error(R.color.white)
                .fit()
                .into(imageView)
            itemView.setOnClickListener {
                onBannerClick.clickBanner(position)
            }
        }
    }


}