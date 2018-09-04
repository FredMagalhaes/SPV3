package ag.strider.protector.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData



class AuthViewModel : AndroidViewModel(Application()) {

    private var mUserRepository: UserRepository? = null
    private var mUsers: MutableLiveData<List<User>>? = null

    init {
        loadUsers()
    }

    private fun loadUsers() {
        // do async operation to fetch users list
        if (mUserRepository == null) {
            mUserRepository = UserRepository.getInstance(UserLocalDataSource(getApplication()))
        }

        mUserRepository.getUsers()
    }

    fun logIn(login: String, password: String): LiveData<Boolean> {
        if (mUsers == null) {
            return LiveData<Boolean> false;
        }

        //TODO: implement login
        return true;
    }

}