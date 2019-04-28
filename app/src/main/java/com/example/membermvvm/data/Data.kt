package com.example.membermvvm.data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("_id")
    val id: String,
    val address: String,
    @SerializedName("appointed_date")
    val appointedDate: String,
    val biography: String,
    @SerializedName("constituency_id")
    val constituencyId: String,
    @SerializedName("constituency_name")
    val constituencyName: String,
    @SerializedName("constituency_type")
    val constituencyType: String,
    val dob: String,
    val education: String,
    val email: String,
    val ethnicity: String,
    val facebook: String,
    @SerializedName("father_name")
    val fatherName: String,
    val gender: String,
    @SerializedName("house_id")
    val houseId: String,
    @SerializedName("house_name")
    val houseName: String,
    val name: String,
    @SerializedName("party_id")
    val partyId: String,
    @SerializedName("party_name")
    val partyName: String,
    val phone: String,
    @SerializedName("previous_occupation")
    val previousOccupation: String,
    @SerializedName("profile_pic")
    val profilePic: String,
    @SerializedName("reference_link")
    val referenceLink: String,
    val religion: String,
    val title: String,
    val twitter: String,
    val youtube: String
)