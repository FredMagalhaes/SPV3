package ag.strider.protector.presentation

import ag.strider.protector.R
import ag.strider.protector.model.Farm
import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.presentation.viewmodel.FarmHallViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class FarmHallActivity : AppCompatActivity() {

    private val TAG: String = LoginActivity::class.java.simpleName

    private lateinit var mFarmHallViewModel: FarmHallViewModel

    private var mUser: User? = null
    private var mFarms: List<Farm>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_hall)

        mFarmHallViewModel = ViewModelProviders.of(this).get(FarmHallViewModel::class.java)
        mFarmHallViewModel.getLoggedUser().observe(this, Observer {
            it.let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        mUser = (it.data as User)
                        loadFarms()
                    }
                    else -> {
                        Log.d(TAG, "user not found. redirecting to login activity")
                        startActivity(Intent(this@FarmHallActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        })
    }

    private fun loadFarms() {
        mUser?.let {
            mFarmHallViewModel.listUserFarms(it.id).observe(this, Observer {
                it.let {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            mFarms = (it.data as List<Farm>)
                            //TODO: recycler list
                            //TODO: hideLoader()
                        }
                        Resource.Status.ERROR -> {
                            Log.w(TAG, it.message)
                        }
                        Resource.Status.LOADING -> {
                            //ignore
                        }
                    }
                }
            })
        }
    }
}
