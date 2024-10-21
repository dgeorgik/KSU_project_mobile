//package com.example.ksu_project_mobile
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.ksu_project_mobile.ui.theme.KSU_project_mobileTheme
//import android.util.Log
//import com.example.ksu_project_mobile.fragments.SignInFragment
//
//
//class OnboardActivity : ComponentActivity() {
//
//    private val TAG = "OnboardActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate called")
//
//        setContent {
//            KSU_project_mobileTheme {
//                MainScreen(onNavigateToLogin = {
//                    navigateToLoginScreen()
//
//                })
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart called")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume called")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause called")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop called")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy called")
//    }
//
//
//
//    private fun navigateToLoginScreen() {
//        val intent = Intent(this, SignInFragment::class.java)
//        startActivity(intent)
//    }
//
//    @Composable
//    fun MainScreen(
//        onNavigateToLogin: () -> Unit,
//
//        ) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(
//                    text = "Добро пожаловать в приложение!",
//                    style = MaterialTheme.typography.titleLarge
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//
//                 Button(onClick = onNavigateToLogin) {
//                    Text("Войти")
//                }
////                Button(onClick = onNavigateToLogin) {
////                    Text("Зарегистрироваться")
////                }
//                Spacer(modifier = Modifier.height(16.dp))
//
//
//            }
//        }
//    }
//
//    @Preview(showBackground = true)
//    @Composable
//    fun MainScreenPreview() {
//        KSU_project_mobileTheme {
//            MainScreen(
//                onNavigateToLogin = {}
//            )
//        }
//    }
//}
