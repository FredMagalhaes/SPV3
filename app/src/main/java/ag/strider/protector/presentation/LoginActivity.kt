package ag.strider.protector.presentation

import ag.strider.protector.R
import ag.strider.protector.presentation.viewmodel.AuthViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG: String = LoginActivity::class.java.simpleName

    private lateinit var mAuthViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuthViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        btn_activityLogin_loginAction.setOnClickListener(View.OnClickListener {

            //TODO: show loading

            val login = editText_loginActivity_login.text.toString()
            val password = editText_loginActivity_password.text.toString()
            logIn(login, password)
        })
    }

    private fun logIn(login: String, password: String) {
        mAuthViewModel.logIn(login, password).observe(this, Observer {

        })
        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
    }
}
