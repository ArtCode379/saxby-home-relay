package saxbybrands.housewares.saxbyhomerelay.data.model

import androidx.annotation.StringRes
import saxbybrands.housewares.saxbyhomerelay.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    KITCHEN(R.string.rqdmv_category_kitchen),
    CLEANING(R.string.rqdmv_category_cleaning),
    COMFORT(R.string.rqdmv_category_comfort),
    LIGHTING(R.string.rqdmv_category_lighting),
    STORAGE(R.string.rqdmv_category_storage),
}
