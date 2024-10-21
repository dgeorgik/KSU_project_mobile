//package com.example.ksu_project_mobile
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.ksu_project_mobile.ui.theme.KSU_project_mobileTheme
//
//class HomeActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            KSU_project_mobileTheme {
//                ChatListScreen()
//            }
//        }
//    }
//}
//
//data class Chat(val sender: String, val message: String, val time: String)
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun ChatListScreen() {
//    val chats = listOf(
//        Chat("Иван", "Привет, как дела?", "10:00"),
//        Chat("Анна", "Сегодня будем встречаться?", "11:15"),
//        Chat("Петр", "Я на месте.", "12:30")
//    )
//
//    Scaffold(modifier = Modifier.fillMaxSize()) {
//        LazyColumn(modifier = Modifier.padding(16.dp)) {
//            items(chats.size) { index ->
//                ChatItem(chat = chats[index])
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun ChatItem(chat: Chat) {
//    Card(modifier = Modifier.fillMaxWidth()) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = chat.sender, style = MaterialTheme.typography.titleMedium)
//            Text(text = chat.message, style = MaterialTheme.typography.bodyMedium)
//            Text(text = chat.time, style = MaterialTheme.typography.bodySmall)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ChatListScreenPreview() {
//    KSU_project_mobileTheme {
//        ChatListScreen()
//    }
//}
