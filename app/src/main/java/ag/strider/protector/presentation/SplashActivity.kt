package ag.strider.protector.presentation

import ag.strider.protector.util.Constants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)

            val firstBoarding = sp.getBoolean(Constants.APP_FIRST_BOARDING, true)
            if (firstBoarding) {
                sp.edit().putBoolean(Constants.APP_FIRST_BOARDING, false).apply()
                continueTo(LoginActivity::class.java)
            }

            val loggedUser = sp.getInt(Constants.LOGGED_USER_ID, Int.MIN_VALUE);
            if (loggedUser == Int.MIN_VALUE)
            {
                continueTo(LoginActivity::class.java)
            } else {
                continueTo(DashboardActivity::class.java)
            }


        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    private fun continueTo(destinationActivity: Class<*>) {
        startActivity(Intent(this@SplashActivity, destinationActivity))
        finish()
    }
}
