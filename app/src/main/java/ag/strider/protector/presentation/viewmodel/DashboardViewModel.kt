package ag.strider.protector.presentation.viewmodel

import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.repositories.UserRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import javax.inject.Inject

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    private lateinit var mUserRepository: UserRepository

    fun getLoggedUser(): LiveData<Resource<User>> {
        return mUserRepository.getLoggedUser()
    }

}