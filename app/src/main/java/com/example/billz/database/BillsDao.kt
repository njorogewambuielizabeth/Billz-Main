package com.example.billz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.billz.model.Bill

@Dao
interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill:Bill)

    @Query("SELECT * FROM bills ORDER BY name")
    fun getAllBills(): LiveData<List<Bill>>
}