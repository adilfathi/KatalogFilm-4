package com.example.katalogfilm_4.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.katalogfilm_4.R
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem

class MovieAdapter(private val itemLiist:List<ResultsItem>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return itemLiist.size
    }

    private fun getItem(position: Int):ResultsItem{
        return itemLiist[position]
    }
    private var mItemClickListener:OnItemClickListener?=null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            val model =getItem(position)

            holder.itemTxtTitle.text=model.title

            if (model.overview?.length!!>50){
                holder.itemTxtOverview.text=model.overview!!.substring(0,49)+"..."
            }else{
                holder.itemTxtOverview.text=model.overview
            }
            Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w185"+model.posterPath).into(holder.imgposter)
        }


    }
    interface OnItemClickListener{
        fun onItemClick(view: View,model: ResultsItem)
    }
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener){
        this.mItemClickListener=mItemClickListener
    }
    inner class ViewHolder internal constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        internal val imgposter:ImageView
        internal val itemTxtTitle:TextView
        internal val itemTxtOverview:TextView

        init {
            this.imgposter=itemView.findViewById( R.id.img_poster)
            this.itemTxtTitle=itemView.findViewById(R.id.item_txt_title)
            this.itemTxtOverview=itemView.findViewById(R.id.item_txt_overview)

            itemView.setOnClickListener { mItemClickListener!!.onItemClick(itemView,itemLiist[adapterPosition]) }
        }
    }

}