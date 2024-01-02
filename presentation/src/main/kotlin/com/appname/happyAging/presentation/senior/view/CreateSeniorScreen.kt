package com.appname.happyAging.presentation.senior.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appname.happyAging.domain.model.senior.Sex
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextFieldWithTitle
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.BottomNavRouter
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.senior.viewmodel.SeniorViewModel
import java.time.LocalDate

enum class RelationWithSenior(
    val korean: String,
    val english: String,
){
    SELF("본인", "self"),
    FAMILY("가족", "family"),
    CARE("돌봄", "care"),
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateSeniorScreen(
    navController: NavController,
) {
    val parentEntry = remember { navController.getBackStackEntry(BottomNavRouter.SENIOR_LIST.routePath) }
    val viewModel : SeniorViewModel = hiltViewModel(parentEntry)
    DefaultLayout(
        title = Router.SENIOR_CREATE.korean,
        actions = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(Icons.Default.Close, contentDescription = "취소")
            }
        }
    ) {
        var seniorName by rememberSaveable { mutableStateOf("") }
        var relation by rememberSaveable { mutableStateOf(RelationWithSenior.SELF) }
        var address by rememberSaveable { mutableStateOf("") }
        var detailAddress by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier.padding(
                horizontal = Sizes.DEFAULT_PADDING,
            ),
        ) {
            Spacer(modifier = Modifier.padding(Sizes.INTERVAL_LARGE4))
            CustomTextFieldWithTitle(text = "정보", value = seniorName , onValueChange = {seniorName = it} )
            CustomTextFieldWithTitle(text = "주소", value = address , onValueChange = {address = it} )
            CustomTextFieldWithTitle(text = "상세주소", value = detailAddress , onValueChange = {detailAddress = it} )
            Text(
                text = "관계",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL4),
            )
            Text(
                text = "앱 가입자와 낙상 위험도 조사 받을 분의 관계를 선택하세요",
                style = TextStyles.CONTENT_SMALL0_STYLE,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            RelationWithSenior.values().forEach {relationWithSenior ->
                RadioButtonRow(text = relationWithSenior.korean, value = relation == relationWithSenior, id = 0, onClick = {
                    relation = relationWithSenior
                })
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CommonButton(
                text = "생성하기",
            ) {
                val params = CreateSeniorParams(
                    name = seniorName,
                    birth = LocalDate.now(),
                    sex = Sex.MALE,
                    address = address,
                    residence = detailAddress,
                    )
                viewModel.createSenior(params)
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
        }
    }
}