package ag.strider.protector.repositories.local

import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.repositories.contracts.IUserDataSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserLocalDataSource : IUserDataSource {

    override fun getLoggedUser(): LiveData<Resource<User>> {
        val ld = MutableLiveData<Resource<User>>()
        ld.value = Resource.error("not implemented", null)
        return ld
    }

}