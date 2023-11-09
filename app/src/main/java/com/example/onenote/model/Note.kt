package com.example.onenote.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//@Parcelize
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var note: String = "",
    var dateTime: Date? = null,
    var image: List<Uri?> = emptyList(),
)
