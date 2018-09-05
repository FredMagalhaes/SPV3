package ag.strider.protector.presentation.viewmodel

import ag.strider.protector.model.Farm
import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.repositories.FarmRepository
import ag.strider.protector.repositories.UserRepository
import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class FarmHallViewModel(application: Application) : AndroidViewModel(application) {

    @Inject private lateinit var mUserRepository: UserRepository
    @Inject private lateinit var mFarmRepository: FarmRepository

    private var mUser : LiveData<Resource<User>>? = null
    private var mFarms : LiveData<Resource<List<Farm>>>? = null

    fun getLoggedUser(): LiveData<Resource<User>> {
        if (mUser == null) {
            mUser = mUserRepository.getLoggedUser()
        }
        return mUser!!
    }

    fun listUserFarms(@NonNull userId: String): LiveData<Resource<List<Farm>>> {
        if (mFarms == null) {
            mFarms = mFarmRepository.listFarmsByUserId(userId)
        }
        return mFarms!!
    }

    fun downloadFarmData(@NonNull farmId: String): LiveData<Resource<Int>> {
        return mFarmRepository.downloadFarmById(farmId)
    }

}