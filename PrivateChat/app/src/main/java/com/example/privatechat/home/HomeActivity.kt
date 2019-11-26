package com.example.privatechat.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.privatechat.R
import com.example.privatechat.models.Message
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeContract.ViewInterface {

    lateinit var rcvMess: RecyclerView
    lateinit var adapter: AdapterListMessage
    lateinit var presenter: HomePresenter
    lateinit var btnSend: Button
    lateinit var editMess: EditText
    lateinit var btnSignOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set view
        rcvMess = findViewById(R.id.rcv_list_mess)
        adapter = AdapterListMessage(this)
        rcvMess.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rcvMess.adapter = adapter

        btnSend = findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            val content = editMess.text.toString()
            presenter.sendMessage(content)
        }
        editMess = findViewById(R.id.edt_mess)

        btnSignOut = findViewById(R.id.btn_sign_out)
        btnSignOut.setOnClickListener {
            presenter.signOut()
        }

        //init presenter
        presenter = HomePresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getAllMessages()
    }

    override fun displayMessage(data: List<Message>) {
        // Log.d("AppLog", "Size ${data.size}")
        this.runOnUiThread {
            adapter.update(data)
            editMess.setText("")
        }
    }

    override fun onSignOut() {
        finish()
    }
}
