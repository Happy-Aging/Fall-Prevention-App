package com.appname.happyAging.domain.model.senior

class SeniorImageModel(
    val id: Long,
    val imageUrl : String,
    val content : String,
) {
    companion object {
        fun fixture(
            id: Long = 1,
            imageUrl: String = "https://picsum.photos/200/200",
            content: String = "이미지 설명",
        ): SeniorImageModel {
            return SeniorImageModel(
                id = id,
                imageUrl = imageUrl,
                content = content,
            )
        }
    }
}