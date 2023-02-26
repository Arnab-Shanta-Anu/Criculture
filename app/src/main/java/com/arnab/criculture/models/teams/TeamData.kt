package com.arnab.criculture.models.teams

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "teams")
@Parcelize
data class TeamData(
    val code: String,
    val country_id: Int,
    @PrimaryKey
    val id: Int,
    val image_path: String,
    val name: String,
    val national_team: Boolean,
    val resource: String,
    val updated_at: String
): Parcelable