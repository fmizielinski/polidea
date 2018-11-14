package com.example.polidea.data.entity

import com.google.gson.annotations.SerializedName

data class Owner(@SerializedName("profile_image") val profileImageUrl: String,
				 @SerializedName("display_name") val displayName: String)