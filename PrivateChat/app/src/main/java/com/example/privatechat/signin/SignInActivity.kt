package com.example.privatechat.signin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.privatechat.R
import com.example.privatechat.signup.SignUpActivity
import com.example.privatechat.home.HomeActivity

class SignInActivity : AppCompatActivity(), SignInContract.ViewInterface {

    lateinit var edtUserName: EditText
    lateinit var edtPassword: EditText
    lateinit var btnSignIn: Button
    lateinit var loadingView: View
    lateinit var btnSignUp: Button

    lateinit var presenter: SignInPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //init presenter
        presenter = SignInPresenter(this)

        // init widget
        edtUserName = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        btnSignIn = findViewById(R.id.btn_sign_in)
        loadingView = findViewById(R.id.loading)
        btnSignUp = findViewById(R.id.btn_sign_up)

        //set button listener
        btnSignIn.setOnClickListener {
            val username = edtUserName.text.toString()
            val password = edtPassword.text.toString()
            presenter.signIn(username + "@chat.com", password)
        }

        btnSignUp.setOnClickListener {
            presenter.onPressSignUp()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun displayLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.INVISIBLE
    }

    override fun onSignInError() {
        Toast.makeText(applicationContext, "Something is wrong!", Toast.LENGTH_LONG).show()
    }

    override fun onSignInSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onPressSignUp() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}
