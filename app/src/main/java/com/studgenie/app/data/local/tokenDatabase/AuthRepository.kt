package com.studgenie.app.data.local.tokenDatabase

import androidx.lifecycle.LiveData

class AuthRepository(private val authDao: AuthDao) {
    val readAllData:LiveData<List<AuthToken>> = authDao.getAuthToken()

    suspend fun addToken(authToken: AuthToken){
        authDao.addToken(authToken)
    }

    suspend fun updateToken(authToken: AuthToken){
        authDao.updateToken(authToken)
    }

    suspend fun update(token:String,pid:Int){
        authDao.update(token, pid)
    }

    suspend fun deleteAuthToken(){
        authDao.deleteAuthToken()
    }
}