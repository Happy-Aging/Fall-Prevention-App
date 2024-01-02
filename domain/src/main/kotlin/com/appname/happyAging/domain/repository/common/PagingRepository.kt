package com.appname.happyAging.domain.repository.common

import com.appname.happyAging.domain.model.common.OffsetPagination

interface PagingRepository<T: Any> {
    suspend fun paginate(page: Int, pageSize: Int): OffsetPagination<T>
}