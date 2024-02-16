package com.example.assignment2.data

import com.example.assignment2.R
import com.example.assignment2.model.Art

class DataSource() {
    fun loadArts(): List<Art> {
        return listOf<Art>(
            Art(R.string.author1, R.drawable.monalisa, R.string.art1, R.string.year1),
            Art(R.string.author2, R.drawable.thescream, R.string.art2, R.string.year2),
            Art(R.string.author3, R.drawable.girlwithapearl, R.string.art3, R.string.year3)
        )}
}