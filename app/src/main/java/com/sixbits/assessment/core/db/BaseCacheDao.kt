package com.sixbits.assessment.core.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseCacheDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMany(data: List<T>)

    @Delete
    fun delete(data: T): Int

    @Update
    fun set(data: T): Int
}
