package com.sixbits.assessment.feature.search.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sixbits.assessment.feature.search.domain.datamodel.SearchItemType
import java.util.Date
import java.util.Calendar

@Entity(tableName = "cache")
data class CacheEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: SearchItemType,
    @ColumnInfo(name = "itemId") val itemId: String,
    @ColumnInfo(name = "date") val createdAt: Date = Calendar.getInstance().time,
    @ColumnInfo(name = "images") val image: String
)
