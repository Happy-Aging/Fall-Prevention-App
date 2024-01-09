package com.appname.happyAging.domain.model.survey

/**
 * 설문조사 질문 모델. [isSubject]가 true이면, 주관식 문제이다.
 *
 * @param type 질문 유형. ui에 표시되기 위한 용도이다.
 */
class SurveyQuestionModel (
    val questionId: Long,
    val number: String,
    val content: String,
    val type: String,
    val isSubject : Boolean,
    val options : List<SurveyQuestionOptionModel>
){
    init {
        require(options.isNotEmpty() xor isSubject)
    }
    companion object{
        fun fixture(
            questionId: Long = 1,
            number: String = "1/5",
            content: String = "최근 3년간 낙상사고를 당한적이 있습니까?",
            type: String = "시니어 정보",
            isSubject: Boolean = false,
            options : List<SurveyQuestionOptionModel>
            = listOf(
                SurveyQuestionOptionModel.fixture(
                    optionId = 1,
                    number = "1",
                    content = "1~5회",
                ),
                SurveyQuestionOptionModel.fixture(
                    optionId = 2,
                    number = "2",
                    content = "6~10회",
                ),
                SurveyQuestionOptionModel.fixture(
                    optionId = 3,
                    number = "3",
                    content = "11~15회",
                ),
                SurveyQuestionOptionModel.fixture(
                    optionId = 4,
                    number = "4",
                    content = "없다.",
                ),
            ),
        ): SurveyQuestionModel {
            return SurveyQuestionModel(
                questionId = questionId,
                number = number,
                content = content,
                type = type,
                isSubject = isSubject,
                options = options,
            )
        }
    }
}

