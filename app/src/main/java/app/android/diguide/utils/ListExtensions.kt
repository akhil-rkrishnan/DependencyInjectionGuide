package app.android.diguide.utils

import app.android.diguide.model.ApiModel

fun List<ApiModel.Entry?>?.asGroupedData() : Map<String, List<ApiModel.Entry?>>? {
    return this?.groupBy {
        it?.category?:"Other"
    }
}