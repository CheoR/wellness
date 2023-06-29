package com.example.wellness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val descriptionResourceId: Int,
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)
