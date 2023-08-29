package com.example.billz.repository

import androidx.lifecycle.LiveData
import com.example.billz.BillzApp
import com.example.billz.database.BillsDao
import com.example.billz.database.BillzDb
import com.example.billz.model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    val db = BillzDb.getDatabase(BillzApp.appContext)
    val billsDao = db.billsDao()


    suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            billsDao.insertBill(bill)
        }
    }

    fun getAllBils(): LiveData<List<Bill>> {
        return  billsDao.getAllBills()
    }
}