package ag.strider.protector.repositories

import ag.strider.protector.model.Farm
import ag.strider.protector.model.Resource
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class FarmRepository {

    //@Inject private lateinit var mFarmLocalDataSource: FarmLocalDataSource
    //@Inject private lateinit var mFarmRemoteDataSource: FarmRemoteDataSource
    //private var farmCache: FarmCache

    @NonNull
    fun listFarmsByUserId(@NonNull userId: String): LiveData<Resource<List<Farm>>> {
        val data = MutableLiveData<Resource<List<Farm>>>()

        data.value = Resource.error("no farms to list", null)

        return data
    }

    @NonNull
    fun downloadFarmById(@NonNull farmId: String): LiveData<Resource<Int>> {
        val data = MutableLiveData<Resource<Int>>()

        //TODO: implement farm download

        return data
    }

}