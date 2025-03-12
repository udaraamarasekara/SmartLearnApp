import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import com.example.smartlearn.ui.theme.lightBlue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlearn.Model.RegistrationAndProfileData
import com.example.smartlearn.ViewModel.TutorProfileViewModel
import com.example.smartlearn.ViewModel.TutorRegistrationViewModel
import kotlinx.coroutines.delay

@Composable
fun TutorProfile(modifier: Modifier=Modifier, navController: NavController)
{
    var email = remember {
        mutableStateOf("")
    }
    var name = remember {
        mutableStateOf("")
    }

    var password = remember {
        mutableStateOf("")
    }
    var passwordConfirmation =remember {
        mutableStateOf("")
    }

    var emailLandscape = remember {
        mutableStateOf("")
    }
    var nameLandscape = remember {
        mutableStateOf("")
    }

    var passwordLandscape = remember {
        mutableStateOf("")
    }
    var passwordConfirmationLandscape =remember {
        mutableStateOf("")
    }
    val viewModel: TutorProfileViewModel = remember { TutorProfileViewModel()}


    var error = viewModel.result




    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    // Set padding based on orientation
    val paddingBottom = if (isPortrait) {
        40.dp  // More padding in portrait mode
    } else {
        20.dp  // Less padding in landscape mode
    }

    val paddingX = if (isPortrait) {
        0.dp  // More padding in portrait mode
    } else {
        20.dp  // Less padding in landscape mode
    }

    Column(
        modifier = modifier.fillMaxSize().background(color = lightBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        if(error.value==true)
        {
            Text(text = "Something went wrong",modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp).background(color = Color.Red ,shape = RoundedCornerShape(8.dp)).padding(30.dp,10.dp,20.dp,10.dp), color = Color.White)
            LaunchedEffect(Unit) {
                delay(1000L) // Non-blocking delay
                error.value = false // Reset error state after delay
            }
        }

        Text(text = "Edit Tutor Profile",modifier= Modifier.padding(0.dp,0.dp,0.dp,paddingBottom), fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        if(isPortrait)
        {
            TextField(value = name.value, onValueChange = { text ->
                name.value=text
            },
                label = {Text("Enter name")},
                modifier= Modifier.padding(0.dp,0.dp,0.dp,paddingBottom).background(color = Color.White)
            )
            TextField(value = email.value, onValueChange = { text ->
                email.value=text
            },
                label = {Text("Enter email")},
                modifier= Modifier.padding(0.dp,0.dp,0.dp,paddingBottom).background(color = Color.White)
            )
            TextField(value = password.value, onValueChange = { text ->
                password.value=text
            },
                visualTransformation =  PasswordVisualTransformation(),
                label = {Text("Enter Password")},
                modifier= Modifier.padding(0.dp,0.dp,0.dp,paddingBottom).background(color = Color.White)
            )
            TextField(value = passwordConfirmation.value, onValueChange = { text ->
                passwordConfirmation.value=text
            },
                visualTransformation =  PasswordVisualTransformation(),
                label = {Text("Confirm Password")},
                modifier= Modifier.padding(0.dp,0.dp,0.dp,paddingBottom).background(color = Color.White)
            )
        }
        else{
            Row(){
                TextField(value = nameLandscape.value, onValueChange = { text ->
                    nameLandscape.value=text
                },
                    label = {Text("Enter name")},
                    modifier= Modifier.padding(0.dp,0.dp,paddingX,paddingBottom).background(color = Color.White)
                )
                TextField(value = emailLandscape.value, onValueChange = { text ->
                    emailLandscape.value=text
                },
                    label = {Text("Enter email")},
                    modifier= Modifier.padding(paddingX,0.dp,0.dp,paddingBottom).background(color = Color.White)
                )
            }

            Row(){
                TextField(value = passwordLandscape.value, onValueChange = { text ->
                    passwordLandscape.value=text
                },
                    visualTransformation =  PasswordVisualTransformation(),
                    label = {Text("Enter Password")},
                    modifier= Modifier.padding(0.dp,0.dp,paddingX,paddingBottom).background(color = Color.White)
                )
                TextField(value = passwordConfirmationLandscape.value, onValueChange = { text ->
                    passwordConfirmationLandscape.value=text
                },
                    visualTransformation =  PasswordVisualTransformation(),
                    label = {Text("Confirm Password")},
                    modifier= Modifier.padding(paddingX,0.dp,0.dp,paddingBottom).background(color = Color.White)
                )
            }
        }
        Row {
            Button(
                onClick = {
                    navController.navigate("TutorDashboard")
                },
                modifier = Modifier.padding(
                    5.dp,
                    paddingBottom,
                    5.dp,
                    0.dp
                )
            ) { Text(text = "Back") }

            Button(
                onClick = {
                    if (isPortrait) {
                        viewModel.updateProfile(
                            RegistrationAndProfileData(
                                name.value.toString(),
                                email.value.toString(),
                                password.value.toString(),
                                passwordConfirmation.value.toString(),
                                ""
                            ), navController
                        )

                    } else {
                        viewModel.updateProfile(
                            RegistrationAndProfileData(
                                nameLandscape.value.toString(),
                                emailLandscape.value.toString(),
                                passwordLandscape.value.toString(),
                                passwordConfirmationLandscape.value.toString(),
                                ""
                            ), navController
                        )
                    }
                },
                modifier = Modifier.padding(0.dp, paddingBottom, 5.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, // Background color of the button
                    contentColor = Color.White  // Color of the text inside the button
                )
            ) { Text(text = "Edit Profile") }
        }
    }
}
