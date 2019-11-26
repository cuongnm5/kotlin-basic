package com.example.privatechat.home

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.privatechat.R
import com.example.privatechat.services.UserApiService
import com.example.privatechat.callback.SingleObserver
import com.example.privatechat.models.User
import com.example.privatechat.models.Message
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class AdapterListMessage(private var activity: Activity) : RecyclerView.Adapter<AdapterListMessage.Holder>() {

    val data = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view: View
        if (viewType == 1) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_message_left, parent, false)
        } else {
            view = LayoutInflater.from(activity).inflate(R.layout.item_message_right, parent, false)
        }
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]
        Log.d("AppLog", "${item.senderId} + ${item.content}")
        holder.tvContent.text = item.content
        UserApiService.getUserById(item.senderId, observer = object: SingleObserver<User> {
            override fun onCompleted(result: User) {
                Log.d("AppLog", "User: ${result.name}")
                activity.runOnUiThread {
                    holder.tvName.text = result.name
                }
            }

            override fun onError(e: Exception) {
                Log.d("AppLog", "Error: ${e.message}")
            }

        })
    }

    override fun getItemViewType(position: Int): Int {
        if (data[position].senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            return 0
        }
        return 1
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvContent: TextView

        init {
            tvName = itemView.findViewById(R.id.tv_name)
            tvContent = itemView.findViewById(R.id.tv_content)
        }
    }

    fun update(data: List<Message>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}