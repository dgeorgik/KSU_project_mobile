package com.example.ksu_project_mobile.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val email: String,
    val password: String,
    val role: String
) : Parcelable
