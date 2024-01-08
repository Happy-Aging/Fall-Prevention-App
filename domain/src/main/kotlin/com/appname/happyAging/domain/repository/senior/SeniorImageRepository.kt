package com.appname.happyAging.domain.repository.senior

import com.appname.happyAging.domain.model.senior.SeniorImageModel
import com.appname.happyAging.domain.params.senior.UpLoadImageParams

interface SeniorImageRepository {
    suspend fun getSeniorImageList() : List<SeniorImageModel>
    suspend fun upLoadSeniorImage(upLoadImageParamsList : List<UpLoadImageParams>)
    suspend fun deleteSeniorImage(id: Long)
}