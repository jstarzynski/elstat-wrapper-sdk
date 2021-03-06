package com.quilmes.wrapper.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.provider.Settings
import com.elstatgroup.elstat.sdk.api.NexoCloud
import com.elstatgroup.elstat.sdk.errror.NexoError
import com.elstatgroup.elstat.sdk.model.NexoAuthenticatedUser
import com.quilmes.wrapper.BuildConfig
import com.quilmes.wrapper.model.SingleResult


class AuthRepository {

    private val authenticateResult = MutableLiveData<SingleResult<NexoAuthenticatedUser>>()
    private val authenticateCallback = object: NexoCloud.NexoAuthenticateCallback {

        override fun onAuthenticate(user: NexoAuthenticatedUser) {
            authenticateResult.postValue(SingleResult(user))
        }

        override fun onError(error: NexoError) {
            authenticateResult.postValue(SingleResult(error))
        }
    }

    fun authenticateUser(context: Context): LiveData<SingleResult<NexoAuthenticatedUser>> {
        NexoCloud.authenticate(context, "${getUUID(context)}${BuildConfig.USERNAME_SUFFIX}", authenticateCallback)
        return authenticateResult
    }

    @SuppressLint("HardwareIds")
    private fun getUUID(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

}