package com.example.billz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billz.model.Bill
import com.example.billz.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel:ViewModel() {
    var billsRepo = BillsRepository()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsRepo.saveBill(bill)
        }
    }

    fun getAllBills(): LiveData<List<Bill>> {
        return billsRepo.getAllBils()
    }

}