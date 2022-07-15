package com.oliver.couchpotato.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliver.couchpotato.api.dto.UserResult
import com.oliver.couchpotato.repositories.CastRepository
import com.oliver.couchpotato.repositories.MovieRepository
import com.oliver.couchpotato.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(private val userRepository: UserRepository): ViewModel() {

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _user: MutableLiveData<UserResult> = MutableLiveData()
    val user: LiveData<UserResult> = _user

    fun getUser() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val resp = userRepository.getUserDetails()
            if (resp.isSuccessful) {
                resp.body()?.also {
                    withContext(Dispatchers.Main) {
                        _isLoading.value = false
                        _user.postValue(it)
                    }
                }
            }
        }
    }
}