package com.quilmes.wrapper.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.elstatgroup.elstat.sdk.model.NexoAuthenticatedUser
import com.quilmes.wrapper.controller.CheckInController
import com.quilmes.wrapper.model.NexoStoreCooler

class StoreViewModel(application: Application) : AndroidViewModel(application) {

    fun checkIn(authenticatedUser: NexoAuthenticatedUser, storeId: String) {
        CheckInController.checkIn(getApplication(), authenticatedUser, storeId)
    }

    fun checkOut(storeId: String) {
        CheckInController.checkOut(getApplication(), storeId)
    }

    fun coolers(storeId: String): LiveData<List<NexoStoreCooler>> = CheckInController.getStream(storeId)

}