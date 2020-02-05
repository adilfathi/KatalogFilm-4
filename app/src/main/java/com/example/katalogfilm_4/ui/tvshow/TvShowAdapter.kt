package com.example.katalogfilm_4.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.katalogfilm_4.R
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow

class TvShowAdapter(private val itemList: List<ResultsItemTvShow>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val model = getItem(position)

            holder.itemTxtTitle.text = model.name

            if (model.overview?.length!! > 50) {
                holder.itemTxtOverview.text = model.overview!!.substring(0, 49) + "..."
            } else {
                holder.itemTxtOverview.text = model.overview
            }
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w185" + model.posterPath).into(holder.imgPoster)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size

    }

    internal fun SetOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    private fun getItem(position: Int): ResultsItemTvShow {
        return itemList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, model: ResultsItemTvShow)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        internal val imgPoster: ImageView = itemView.findViewById(R.id.img_poster)
        internal val itemTxtTitle: TextView = itemView.findViewById(R.id.item_txt_title);
        internal val itemTxtOverview: TextView = itemView.findViewById(R.id.item_txt_overview)

        init {
            itemView.setOnClickListener {
                mItemClickListener!!.onItemClick(
                    itemView,
                    itemList[adapterPosition]
                )
            }
        }
    }
}