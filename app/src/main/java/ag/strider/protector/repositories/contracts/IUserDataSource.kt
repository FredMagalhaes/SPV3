package ag.strider.protector.repositories.contracts

import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import androidx.lifecycle.LiveData

interface IUserDataSource {

    fun getLoggedUser(): LiveData<Resource<User>>

}