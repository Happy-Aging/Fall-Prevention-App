package com.appname.happyAging.presentation.senior.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import coil.compose.AsyncImage
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.utils.noRippleClickable

@Composable
fun SeniorImageScreen(
    backButtonAction : (() -> Unit) = {},
    id: Long,
){
    DefaultLayout(
        title = "사진 찍기",
        backButtonAction = backButtonAction,
    ) {
        Column {
            ImageCategoryItem(title = "화장실", images = listOf())
        }
    }
}

@Composable
fun ImageCategoryItem(
    title: String,
    images : List<String>,
){
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { result ->
            selectedImageUris = result
        }
    )
    LazyColumn{
        item {
            Text(text = title)
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                images.forEach { image ->
                    AsyncImage(model = image, contentDescription =null)
                }
                Spacer(modifier = Modifier.width(Sizes.INTERVAL4))
                Box(
                    modifier = Modifier
                        .size(104.dp)
                        .border(
                            width = 1.dp,
                            color = Colors.DIVIDER_GREY,
                        )
                        .noRippleClickable {
                            multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                            val file = selectedImageUris.map {
                                 it.toFile()
                            }
                        },
                    contentAlignment = Alignment.Center,
                ){
                    Text(text = "+")
                }
                selectedImageUris.forEach { uri ->
                    AsyncImage(
                        modifier = Modifier.size(104.dp),
                        model = uri,
                        contentDescription = null
                    )
                }
            }
        }




    }
}


@Preview
@Composable
fun SeniorImageScreenPreview(){
    SeniorImageScreen(id = 1)
}