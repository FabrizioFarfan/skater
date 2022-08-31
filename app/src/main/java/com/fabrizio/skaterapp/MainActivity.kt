package com.fabrizio.skaterapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity(), SkaterTileInterface {


    lateinit var skateTileList: ArrayList<SkateTile>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        skateTileList = getSkaterTileList()



        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainerView, ListFragment())
        }

    }


    override fun onLearnMoreButtonClicked(position: Int) {
           val skateTile = skateTileList[position]

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            val bundle= Bundle().apply {
                putString("SkateTileId", skateTile.id)
            }
            setCustomAnimations(
                com.google.android.material.R.anim.fragment_open_enter,
                com.google.android.material.R.anim.fragment_fade_exit,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            replace(R.id.fragmentContainerView, DetailFragment().apply {
                arguments= bundle
            })
        }


    }

    override fun onFavoriteClicked(position: Int) {
        val skateTile = skateTileList[position]
        skateTile.isFavorite = !skateTile.isFavorite

        (supportFragmentManager.fragments[0] as ListFragment).onFavoriteClicked(position)

        SharedPrefUtil.setSkaterTileFavorite(skateTile.id, skateTile.isFavorite)

    }

    private fun getSkaterTileList(): ArrayList<SkateTile> {
        return ArrayList<SkateTile>().apply {
            add(
                SkateTile(
                    id = "tony_hawk",
                    title = "Tony Hawk",
                    description = "Description of the skater",
                    descriptionLong = "A longer description of the skater that wouldn't fit on a line",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.tony_hawk,
                    headerImageUrl = "https://assets.reedpopcdn.com/news-videogiochi-nuovo-tony-hawk-non-con-activision-1485855017101.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/news-videogiochi-nuovo-tony-hawk-non-con-activision-1485855017101.jpg",
                    skaterUrl = "https://www.youtube.com/watch?v=L-Wd4A8ESyk",
                    isFavorite = SharedPrefUtil.getSkaterTileFavorite("tony_hawk")
                )
            )

            add(
                SkateTile(
                    id = "angelo_caro",
                    title = "Angelo Caro",
                    description = "Description of the skater",
                    descriptionLong = "A longer description of the skater that wouldn't fit on a line",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.angelo_caro,
                    headerImageUrl = "https://media.gettyimages.com/photos/perus-angelo-caro-narvaez-competes-in-the-mens-street-final-during-picture-id1234167722?s=2048x2048",
                    skaterUrl = "https://www.youtube.com/watch?v=c7DxXAbqGsk",
                    isFavorite = SharedPrefUtil.getSkaterTileFavorite("angelo_caro")
                )
            )
            add(
                SkateTile(
                    id = "chris_cole",
                    title = "Chris Cole",
                    description = "Description of the skater",
                    descriptionLong = "A longer description of the skater that wouldn't fit on a line",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.chris_cole,
                    headerImageUrl = "https://i.pinimg.com/564x/48/1e/20/481e20389347819ed1bfe05b3eba73ab.jpg",
                    skaterUrl = "https://www.youtube.com/watch?v=PXOrjJhwUTM",
                    isFavorite = SharedPrefUtil.getSkaterTileFavorite("chris_cole")
                )
            )

            add(
                SkateTile(
                    id = "luan_oliveira",
                    title = "Luan Oliveira",
                    description = "Description of the skater",
                    descriptionLong = "A longer description of the skater that wouldn't fit on a line",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.luan_oliveira,
                    headerImageUrl = "https://i.pinimg.com/564x/bb/2b/ca/bb2bca1b761bfe31c96255a5ba64f5c0.jpg",
                    skaterUrl = "https://www.youtube.com/watch?v=KyYJ-rZyMD0",
                    isFavorite = SharedPrefUtil.getSkaterTileFavorite("luan_oliveira")
                )
            )
            add(
                SkateTile(
                    id = "nyjah_huston",
                    title = "Nyjah Huston",
                    description = "Description of the skater",
                    descriptionLong = "A longer description of the skater that wouldn't fit on a line",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.nyjah_huston,
                    headerImageUrl = "https://www.sikids.com/.image/c_limit%2Ccs_srgb%2Cq_auto:good%2Cw_600/MTY4NTE5MzQ3NDA3NTYyNTE5/image-placeholder-title.webp",
                    skaterUrl = "https://www.youtube.com/watch?v=0_IRcOvvuKw",
                    isFavorite = SharedPrefUtil.getSkaterTileFavorite("nyjah_huston")
                )
            )
            add(
                SkateTile(
                    id = "tom_asta",
                    title = "Tom Asta",
                    description = "Description of the skater",
                    descriptionLong = "A longer description of the skater that wouldn't fit on a line",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.tom_asta,
                    headerImageUrl = "https://www.4actionsport.it/wp-content/uploads/2017/01/1484045311759.jpg",
                    skaterUrl = "https://www.youtube.com/watch?v=KgSLOW67G9I",
                    isFavorite = SharedPrefUtil.getSkaterTileFavorite("tom_asta")
                )
            )
        }
    }

}
