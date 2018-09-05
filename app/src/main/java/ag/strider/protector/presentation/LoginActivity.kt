package ag.strider.protector.presentation

import ag.strider.protector.R
import ag.strider.protector.model.Resource
import ag.strider.protector.model.User
import ag.strider.protector.presentation.viewmodel.AuthViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
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

        editText_loginActivity_login.addTextChangedListener(mFormFieldsWatcher)
        editText_loginActivity_password.addTextChangedListener(mFormFieldsWatcher)

        btn_activityLogin_loginAction.isClickable = false
        btn_activityLogin_loginAction.setOnClickListener {
            val login = editText_loginActivity_login.text.toString()
            val password = editText_loginActivity_password.text.toString()

            if (login != "" && password != "") {
                showLoader()
                logIn(login, password)
            }
        }
    }

    private fun logIn(login: String, password: String) {
        mAuthViewModel.logUserIn(login, password).observe(this, Observer {

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.v(TAG, "${(it.data as User).id} successfully logged in")
                    continueTo(FarmHallActivity::class.java)
                }
                Resource.Status.ERROR -> {
                    Log.v(TAG, "error while trying to log in")
                    hideLoader() //TODO: check if this is the best place for loader dismiss
                    handleLoginError(it.message)
                }
                Resource.Status.LOADING -> {
                    // ignore
                }
            }

        })
    }

    private fun handleLoginError(@Nullable errorMsg: String?) {
        if (errorMsg == null) {
            Log.w(TAG, "unknown login error reason")
            return
        }

        when (errorMsg) {
            "OI" -> {}
            "TCHAU" -> {}
            else -> {}
        }
    }

    //TODO: create loader layout and use it here
    private fun showLoader() { }
    private fun hideLoader() { }

    private val mFormFieldsWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // safe to ignore
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // safe to ignore
        }

        override fun afterTextChanged(p0: Editable?) {
            checkFormCredentialsValidity()
        }
    }

    private fun checkFormCredentialsValidity() {
        btn_activityLogin_loginAction.isClickable =
                ( editText_loginActivity_login.text.toString() != ""
                && editText_loginActivity_password.text.toString() != "" )
    }

    private fun continueTo(@NonNull destinationActivity: Class<*>) {
        startActivity(Intent(this@LoginActivity, destinationActivity))
        finish()
    }
}
