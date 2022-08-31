package com.fabrizio.skaterapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SkaterTileAdapter(
    private val data: ArrayList<SkateTile>,
    private val skaterTileInterface: SkaterTileInterface
) : RecyclerView.Adapter<SkaterTileAdapter.SkaterTileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkaterTileViewHolder {
        return SkaterTileViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SkaterTileViewHolder, position: Int) {
        holder.onBind(data[position], skaterTileInterface)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SkaterTileViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_skate_tile, parent, false)
    ) {
        private val headerImageView: ImageView = itemView.findViewById(R.id.skaterHeaderImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val button: MaterialButton = itemView.findViewById(R.id.button)
        private val favoriteImageView: AppCompatImageView =
            itemView.findViewById(R.id.favoriteImageView)

        fun onBind(skateTile: SkateTile, skaterTileInterface: SkaterTileInterface) {

            headerImageView.setImageResource(skateTile.headerImageResId)
            titleTextView.text = skateTile.title
            descriptionTextView.text = skateTile.description
            button.setOnClickListener {
                skaterTileInterface.onLearnMoreButtonClicked(adapterPosition)
            }
            val icon =
                if (skateTile.isFavorite) R.drawable.ic_sharp_favorite_24 else R.drawable.ic_round_favorite_outline_24dp
            favoriteImageView.setImageResource(icon)
            favoriteImageView.setOnClickListener {
                skaterTileInterface.onFavoriteClicked(adapterPosition)
            }

        }
    }


}