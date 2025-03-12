package com.example.smartlearn.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import com.example.smartlearn.ViewModel.LoginViewModel
import com.example.smartlearn.ui.theme.lightBlue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlearn.Model.LoginData
import com.example.smartlearn.Model.WelcomePageMsg

@Composable
fun WelcomePage(modifier: Modifier=Modifier,navController: NavController)
{

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    // Set padding based on orientation
    val paddingBottom = if (isPortrait) {
        40.dp  // More padding in portrait mode
    } else {
        20.dp  // Less padding in landscape mode
    }

    Column(
        modifier = modifier.fillMaxSize().background(color = lightBlue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, paddingBottom).fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
        ) {


            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Welcome to Past Paper Management System",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingBottom),
                    fontSize = 30.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(
                    text = "Department Of Computer Science",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingBottom),
                    fontSize = 40.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Button(
                    onClick = {
                        navController.navigate("Login")
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 0.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login")
                }

                Button(
                    onClick = {
                        navController.navigate("Registration")
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 0.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Register")
                }



                Text(
                    text = "University of Ruhuna",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingBottom),
                    fontSize = 20.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

            }
                if (WelcomePageMsg.msg != null) {
                    Text(text = WelcomePageMsg.msg!!,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)
                            .background(color = Color.Green, shape = RoundedCornerShape(8.dp))
                            .padding(30.dp, 10.dp, 20.dp, 10.dp),
                        color = Color.White
                    )
                    WelcomePageMsg.msg = null
                }
            }
        }
    }
