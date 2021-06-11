package com.gk.app.footballapp.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.gk.app.footballapp.databinding.RecyclerItemTeamBinding
import com.gk.app.footballapp.view.image.ImageLoader

/**
 * [RecyclerView.Adapter] that can display a [TeamListItem].
 */
class TeamItemRecyclerViewAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: (TeamListItem) -> Unit
) : RecyclerView.Adapter<TeamItemRecyclerViewAdapter.ViewHolder>() {

    private val values = ArrayList<TeamListItem>()

    fun updateItems(newValues: List<TeamListItem>) {
        values.clear()
        values.addAll(newValues)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemTeamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        item.imageUrl?.let { imageLoader.loadImage(holder.imageView, it) }
        holder.imageView.contentDescription = item.teamName
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = values.size

    @VisibleForTesting
    class ViewHolder(binding: RecyclerItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.teamItemImage

        override fun toString(): String {
            return super.toString() + " '" + imageView.contentDescription + "'"
        }
    }

}