package com.pth.androidapp.data.models

data class ListingsResponse<T>(
    val content: List<T>?,
    val empty: Boolean?,
    val first: Boolean?,
    val last: Boolean?,
    val number: Int?,
    val numberOfElements: Int?,
    val pageable: Pageable?,
    val size: Int?,
    val sort: Sort?,
    val totalElements: Int?,
    val totalPages: Int?
)

data class Pageable(
    val pageNumber: Int?,
    val pageSize: Int?,
    val sort: Sort?,
    val offset: Int?
)

data class Sort(
    val empty: Boolean?,
    val sorted: Boolean?,
    val unsorted: Boolean?
)