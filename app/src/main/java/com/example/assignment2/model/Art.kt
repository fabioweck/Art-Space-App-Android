package com.example.assignment2.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Art(
    @StringRes val stringAuthorId: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val stringArtId: Int,
    @StringRes val yearId: Int
)
