package com.example.some

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val messageList: List<String>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        // Используем LayoutInflater для создания View на основе XML разметки message_item_layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item_layout, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        // Привязка данных к элементу ViewHolder
        val message = messageList[position]
        holder.messageText.text = message
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    // Определяем ViewHolder, который будет содержать элементы пользовательского интерфейса для одного сообщения
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Находим элемент TextView в макете
        var messageText: TextView = itemView.findViewById(R.id.messageText)
    }
}

