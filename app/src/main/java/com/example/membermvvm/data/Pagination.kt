package com.example.membermvvm.data

import com.google.gson.annotations.SerializedName

data class Pagination(
    val count: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    val links: Links,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)