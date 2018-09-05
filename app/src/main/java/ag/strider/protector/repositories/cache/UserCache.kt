package ag.strider.protector.repositories.cache

import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.repositories.contracts.IUserDataSource
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserCache {

    private var mUser: User? = null

    @Nullable
    fun getLoggedUser(): User? {
        return mUser
    }

    fun setLoggedUser(@NonNull user: User) {
        mUser = user
    }

}