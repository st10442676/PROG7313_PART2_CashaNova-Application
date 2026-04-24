package com.example.cashanovaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CashaNovaTheme {
                var currentScreen by rememberSaveable { mutableStateOf("login") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentScreen) {
                            "register" -> {
                                BackHandler { currentScreen = "login" }
                                RegisterScreen(onSignUp = { currentScreen = "login" })
                            }

                            else -> LoginScreen(onRegisterClick = { currentScreen = "register" })
                        }
                    }
                }
            }
        }
    }
}

private val Gold = Color(0xFFE0B120)
private val White = Color(0xFFF6F6F6)
private val FieldBorder = Color(0xFF8D8D8D)
private val FieldFill = Color(0x33FFFFFF)

@Composable
fun CashaNovaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Gold,
            background = Color.Black,
            surface = Color.Black,
            onPrimary = Color.Black,
            onBackground = White,
            onSurface = White
        ),
        content = content
    )
}

@Composable
fun LoginScreen(onRegisterClick: () -> Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))

            Image(
                painter = painterResource(id = R.drawable.cashanova_logo),
                contentDescription = "CashaNova Logo",
                modifier = Modifier
                    .fillMaxWidth(0.72f)
                    .height(180.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "WELCOME USER",
                color = White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            LabeledField(
                label = "USERNAME:",
                value = username,
                onValueChange = { username = it }
            )

            SmallLinkText("Forgot your username?")

            Spacer(modifier = Modifier.height(12.dp))

            LabeledField(
                label = "PASSWORD:",
                value = password,
                onValueChange = { password = it },
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )

            SmallLinkText("Forgot your password?")

            Spacer(modifier = Modifier.height(28.dp))

            GoldPrimaryButton(
                text = "LOGIN",
                onClick = { },
                modifier = Modifier.fillMaxWidth(0.62f)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "No Account? ", color = White, fontSize = 15.sp)
                Text(
                    text = "REGISTER NOW",
                    color = Gold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun RegisterScreen(onSignUp: () -> Unit) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var contactNumber by rememberSaveable { mutableStateOf("") }
    var day by rememberSaveable { mutableStateOf("") }
    var month by rememberSaveable { mutableStateOf("") }
    var year by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(42.dp))

            GoldGradientTitle("REGISTRATION")

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    LabeledField(
                        label = "Full Name",
                        value = fullName,
                        onValueChange = { fullName = it },
                        labelFontSize = 14.sp
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    LabeledField(
                        label = "Surname",
                        value = surname,
                        onValueChange = { surname = it },
                        labelFontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LabeledField(
                label = "Email Address",
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email,
                labelFontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Contact Number",
                color = White,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, bottom = 6.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(96.dp)
                        .height(56.dp)
                        .background(FieldFill, RoundedCornerShape(22.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🇿🇦 +27", color = White, fontSize = 16.sp)
                }

                OutlinedTextField(
                    value = contactNumber,
                    onValueChange = { contactNumber = it.filter(Char::isDigit).take(10) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(22.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = fieldColors()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            DateOfBirthField(
                day = day,
                month = month,
                year = year,
                onDayChange = { day = it },
                onMonthChange = { month = it },
                onYearChange = { year = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabeledField(
                label = "Username",
                value = username,
                onValueChange = { username = it },
                labelFontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabeledField(
                label = "Create Password",
                value = password,
                onValueChange = { password = it },
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible },
                labelFontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabeledField(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                isPassword = true,
                passwordVisible = confirmPasswordVisible,
                onTogglePasswordVisibility = { confirmPasswordVisible = !confirmPasswordVisible },
                labelFontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            GoldPrimaryButton(
                text = "SIGN UP  →",
                onClick = onSignUp,
                modifier = Modifier.fillMaxWidth(0.72f)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AppBackground(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = "App Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.25f))
        )

        content()
    }
}

@Composable
fun GoldGradientTitle(text: String) {
    Text(
        text = text,
        fontSize = 34.sp,
        fontWeight = FontWeight.ExtraBold,
        style = TextStyle(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFF0B2),
                    Color(0xFFE0B120),
                    Color(0xFF8C6500)
                )
            )
        )
    )
}

@Composable
fun SmallLinkText(text: String) {
    Text(
        text = text,
        color = White.copy(alpha = 0.85f),
        fontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, start = 6.dp)
    )
}

@Composable
fun DateOfBirthField(
    day: String,
    month: String,
    year: String,
    onDayChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onYearChange: (String) -> Unit
) {
    Text(
        text = "Date of Birth",
        color = White,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, bottom = 6.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = day,
            onValueChange = { value ->
                if (value.length <= 2 && value.all(Char::isDigit)) onDayChange(value)
            },
            modifier = Modifier.weight(1f),
            singleLine = true,
            shape = RoundedCornerShape(22.dp),
            placeholder = { Text("DD", color = White.copy(alpha = 0.7f)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = fieldColors()
        )

        OutlinedTextField(
            value = month,
            onValueChange = { value ->
                if (value.length <= 2 && value.all(Char::isDigit)) onMonthChange(value)
            },
            modifier = Modifier.weight(1f),
            singleLine = true,
            shape = RoundedCornerShape(22.dp),
            placeholder = { Text("MM", color = White.copy(alpha = 0.7f)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = fieldColors()
        )

        OutlinedTextField(
            value = year,
            onValueChange = { value ->
                if (value.length <= 4 && value.all(Char::isDigit)) onYearChange(value)
            },
            modifier = Modifier.weight(1.4f),
            singleLine = true,
            shape = RoundedCornerShape(22.dp),
            placeholder = { Text("YYYY", color = White.copy(alpha = 0.7f)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = fieldColors()
        )
    }
}

@Composable
fun LabeledField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    labelFontSize: TextUnit = 18.sp
) {
    Text(
        text = label,
        color = White,
        fontSize = labelFontSize,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, bottom = 6.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(22.dp),
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (isPassword && onTogglePasswordVisibility != null) {
                Text(
                    text = if (passwordVisible) "HIDE" else "SHOW",
                    color = Gold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { onTogglePasswordVisibility() }
                )
            }
        },
        colors = fieldColors()
    )
}

@Composable
fun GoldPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Gold,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = White,
    unfocusedTextColor = White,
    focusedContainerColor = FieldFill,
    unfocusedContainerColor = FieldFill,
    focusedBorderColor = Gold.copy(alpha = 0.75f),
    unfocusedBorderColor = FieldBorder,
    cursorColor = Gold
)
