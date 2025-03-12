package com.example.smartlearn.View

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import com.example.smartlearn.ui.theme.lightBlue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlearn.ViewModel.UploadPaperViewModel

@Composable
fun UploadPaperPage(modifier: Modifier = Modifier, navController: NavController) {
    var paper = remember { mutableStateOf<Uri?>(null) }
    var name = remember {
        mutableStateOf("")
    }
    var uploadStatus = remember { mutableStateOf("") }

    val viewModel: UploadPaperViewModel = remember { UploadPaperViewModel() }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        paper.value = uri
    }
    var error = viewModel.fileUploadStatus.value
    val context = LocalContext.current




    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = lightBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            TextField(
                value = name.value, onValueChange = { text ->
                    name.value = text
                },
                label = { Text("Paper name") },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
                    .background(color = Color.White)
            )
        }
        if (error == -1) {
            Text(
                text = "Something went wrong",
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(8.dp))
                    .padding(30.dp, 10.dp, 20.dp, 10.dp),
                color = Color.White
            )
        } else if (error == 1) {
            Text(
                text = "Done",
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    .background(color = Color.Green, shape = RoundedCornerShape(8.dp))
                    .padding(30.dp, 10.dp, 20.dp, 10.dp),
                color = Color.White
            )

        }

        Text(
            text = "Upload Paper",
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )


        Button(onClick = { filePickerLauncher.launch("*/*") }) {
            Text("Select File")
        }

        Spacer(modifier = Modifier.height(16.dp))

        paper.value?.let {
            Text("Selected File: ${it.path}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if(name.value!="") {
                    uploadStatus.value = "Uploading"
                    paper.value.let { uri ->
                        viewModel.uploadPaper(fileUri = uri, name = name.value, context = context)
                    }
                }  else{
                    uploadStatus.value = "Please enter a name"
                } }

            ) {
                Text("Upload")
            }
        }
        if (uploadStatus.value != "") {
            uploadStatus.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Status: ${it.value}")
            }
        }
        Button(onClick = {
            navController.navigate("TutorDashboard")
        },
            modifier = Modifier.padding(
                5.dp,
                10.dp,
                0.dp,
                0.dp
            )) { Text(text = "Back") }
    }
}
