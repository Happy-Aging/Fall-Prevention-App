package com.appname.happyAging.domain.model.common

class OffsetPagination<T> (
    val hasNext: Boolean,
    val data : List<T>
)