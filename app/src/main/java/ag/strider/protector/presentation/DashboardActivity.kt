package ag.strider.protector.presentation

import ag.strider.protector.R
import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.presentation.viewmodel.DashboardViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = DashboardActivity::class.java.simpleName

    private lateinit var mDashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mDashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        mDashboardViewModel.getLoggedUser().observe(this, Observer {
            it.let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        updateUI((it.data as User))
                    }
                    else -> {
                        Log.d(TAG, "user not found. redirecting to login activity")
                        startActivity(Intent(this@DashboardActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        })

        card_dashboardActivity_agenda.setOnClickListener(this)
        card_dashboardActivity_areas.setOnClickListener(this)
        card_dashboardActivity_settings.setOnClickListener(this)
    }

    private fun updateUI(@NonNull user: User) {
        // TODO: update ui with logged user info
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                card_dashboardActivity_agenda.id -> continueTo(AgendaActivity::class.java)
                card_dashboardActivity_areas.id -> continueTo(AreasActivity::class.java)
                card_dashboardActivity_settings.id -> continueTo(SettingsActivity::class.java)
                else -> Log.e(TAG, "Unknown onclick id origin")
            }
        }
    }

    private fun continueTo(@NonNull destinationActivity: Class<*>) {
        startActivity(Intent(this@DashboardActivity, destinationActivity))
        finish()
    }

}
