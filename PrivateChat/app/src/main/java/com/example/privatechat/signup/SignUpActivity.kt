package com.example.privatechat.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.privatechat.R
import com.example.privatechat.home.*

class SignUpActivity : AppCompatActivity(), SignUpContract.ViewInterface {

    lateinit var presenter: SignUpPresenter
    lateinit var edtName: EditText
    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    lateinit var edtConfirm: EditText
    lateinit var btnSignUp: Button
    lateinit var loadingView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //set view
        edtName = findViewById(R.id.edt_name)
        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        edtConfirm = findViewById(R.id.edt_confirm_password)
        btnSignUp = findViewById(R.id.btn_sign_up)
        btnSignUp.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            val confirm  = edtConfirm.text.toString()
            val name = edtName.text.toString()

            if (!password.equals(confirm)) {
                Toast.makeText(applicationContext, "Confirm password is invalid!!!", Toast.LENGTH_LONG).show()
            } else {
                presenter.signUp(name, username + "@chat.com", password)
            }
        }

        loadingView = findViewById(R.id.loading)

        presenter = SignUpPresenter(this)
    }

    override fun displayLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.INVISIBLE
    }

    override fun onSignUpSuccess() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun onSignUpFalse() {
        Toast.makeText(this, "Something is wrong!", Toast.LENGTH_LONG).show()
    }
}
