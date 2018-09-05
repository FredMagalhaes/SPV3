package ag.strider.protector.repositories

import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.repositories.contracts.IUserDataSource
import ag.strider.protector.repositories.local.UserLocalDataSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton
import ag.strider.protector.repositories.cache.UserCache


@Singleton
class UserRepository @Inject constructor() : IUserDataSource {

    @Inject private lateinit var mUserLocalDataSource: UserLocalDataSource
    //@Inject private lateinit var mUserRemoteDataSource: UserRemoteDataSource
    private var userCache: UserCache

    init {
        userCache = UserCache()
    }

    override fun getLoggedUser(): LiveData<Resource<User>> {
        val data = MutableLiveData<Resource<User>>()

        // search in cache
        val cached = userCache.getLoggedUser()
        if (cached != null) {
            data.value = Resource.success(cached)
            return data
        }

        // search in local db
        val local = mUserLocalDataSource.getLoggedUser()
        if (local.value == Resource.Status.SUCCESS) {
            userCache.setLoggedUser((local.value as Resource<User>).data!!)
        }

        return local
    }

    fun logUserIn(login: String, password: String): LiveData<Resource<User>> {
        val data = MutableLiveData<Resource<User>>()

        val user = User("neo")
        data.value =  Resource.error("not implemented", user)

        //TODO: implement login backend

        return data
    }


}