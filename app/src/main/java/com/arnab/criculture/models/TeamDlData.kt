package com.arnab.criculture.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TeamDlData(
    val overs: @RawValue Any?,
    val score: @RawValue Any?,
    val wickets_out: @RawValue Any?
): Parcelable