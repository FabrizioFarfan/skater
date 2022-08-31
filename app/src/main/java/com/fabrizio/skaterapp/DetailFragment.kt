package com.fabrizio.skaterapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.squareup.picasso.Picasso

class DetailFragment : BaseMainActivityFragment(R.layout.fragment_detail) {

    private val skateTile: SkateTile by lazy {
        mainActivity.skateTileList.find {

        it.id == requireArguments().getString("SkateTileId") } ?: SkateTile()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(true)
        }

        setToolbarview(title = "Skaters Overview")

        val headerImageView: ImageView = view.findViewById(R.id.skaterHeaderImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val descriptionLongTextView: TextView = view.findViewById(R.id.descriptionLongTextView)


        titleTextView.text = skateTile.title
        descriptionTextView.text = skateTile.description
        descriptionLongTextView.text = skateTile.descriptionLong
        // headerImageView.setImageResource(skateTile.headerImageResId) Picasso replace this command
        Picasso.get()
            .load(skateTile.headerImageUrl)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(headerImageView)


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_skater_tile_detail, menu)
                if (skateTile.isFavorite) {
                    menu.findItem(R.id.menuItemFavorite)?.setIcon(R.drawable.ic_sharp_favorite_24)
                }

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        (activity as MainActivity).supportFragmentManager.popBackStack()
                        true
                    }
                    R.id.menuItemLink -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(skateTile.skaterUrl))
                        startActivity(intent)
                        true
                    }
                    R.id.menuItemFavorite -> {
                        val isCurrentlyFavorite = skateTile.isFavorite
                        if (isCurrentlyFavorite) {
                            menuItem.setIcon(R.drawable.ic_round_favorite_outline_24dp)
                        } else {
                            menuItem.setIcon(R.drawable.ic_sharp_favorite_24)
                        }

                        skateTile.isFavorite = !isCurrentlyFavorite

                        SharedPrefUtil.setSkaterTileFavorite(skateTile.id, skateTile.isFavorite)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    }

