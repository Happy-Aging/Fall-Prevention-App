package com.appname.happyAging.domain.params.senior

import com.appname.happyAging.domain.model.senior.Sex
import java.time.LocalDate

class CreateSeniorParams(
    val name: String,
    val birth: LocalDate,
    val sex: Sex,
    val address: String,
    val residence: String,
){
    companion object{
        fun fixture() : CreateSeniorParams{
            return CreateSeniorParams(
                "김복자",
                LocalDate.now(),
                Sex.MALE,
                "서울시 강남구",
                "서울시 강남구",
            )
        }
    }
}