package com.lws.lwsmediaplayer.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lws.lwsmediaplayer.R
import com.lws.lwsmediaplayer.data.model.ResultItunes
import com.lws.lwsmediaplayer.databinding.ItemItuneBinding

class MainAdapter(private val list: List<ResultItunes>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemItuneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainViewHolder(private val item: ItemItuneBinding,
                         private val context: Context) : RecyclerView.ViewHolder(item.root) {

        private val listener: RecyclerviewListener? = null

        fun bindData(data: ResultItunes) {
            item.tvTrack.text = data.trackName
            item.tvAlbum.text = data.collectionName
            item.tvArtist.text = data.artistName

            Glide.with(context)
                .load(data.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(item.imageview)

            item.containerPerItem.setOnClickListener {
                listener?.onClickItem(data.previewUrl)
            }
        }
    }
}