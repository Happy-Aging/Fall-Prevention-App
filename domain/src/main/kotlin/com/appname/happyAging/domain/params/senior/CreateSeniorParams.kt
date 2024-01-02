package com.appname.happyAging.domain.params.senior

import com.appname.happyAging.domain.model.senior.Sex
import java.time.LocalDate

class CreateSeniorParams(
    val name: String,
    val birth: LocalDate,
    val sex: Sex,
    val address: String,
    val residence: String,
)