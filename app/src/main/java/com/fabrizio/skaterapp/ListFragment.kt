package com.fabrizio.skaterapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class ListFragment: BaseMainActivityFragment(R.layout.fragment_list) {

    private lateinit var skaterTileAdapter : SkaterTileAdapter
    private val skateTileList: ArrayList<SkateTile>
        get() = mainActivity.skateTileList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
        setToolbarview("Skaters")

        skaterTileAdapter = SkaterTileAdapter(skateTileList, mainActivity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = skaterTileAdapter

    }


    override fun onResume() {
        super.onResume()
        skaterTileAdapter.notifyDataSetChanged()
    }

    fun onFavoriteClicked(position: Int) {
        skaterTileAdapter.notifyDataSetChanged()

    }


}