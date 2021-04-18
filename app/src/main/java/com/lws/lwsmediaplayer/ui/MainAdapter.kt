package com.lws.lwsmediaplayer.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lws.lwsmediaplayer.R
import com.lws.lwsmediaplayer.data.model.ResultItunes
import com.lws.lwsmediaplayer.databinding.ItemItuneBinding

class MainAdapter(private val itunes: List<ResultItunes>,
                  private val mListener: (itune: ResultItunes, position: Int) -> Unit
                  ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemItuneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(itunes[position])
        holder.itemView.setOnClickListener {
            mListener.invoke(itunes[position], holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return itunes.size
    }

    class MainViewHolder(private val item: ItemItuneBinding,
                         private val context: Context) : RecyclerView.ViewHolder(item.root) {

        fun bindData(data: ResultItunes) {

            item.containerPerItem.setBackgroundResource(R.color.white)
            item.visualizer.visibility = View.GONE

            item.tvTrack.text = data.trackName
            item.tvAlbum.text = data.collectionName
            item.tvArtist.text = data.artistName

            Glide.with(context)
                    .load(data.artworkUrl60)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(item.imageview)

        }
    }
}