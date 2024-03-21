package com.example.submission_awal_fundamental.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission_awal_fundamental.data.response.ItemsItem
import com.example.submission_awal_fundamental.databinding.ItemUserBinding
import com.example.submission_awal_fundamental.ui.DetailUserActivity

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUser = ArrayList<ItemsItem>()


    fun setData(items: List<ItemsItem>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = listUser[position]
        holder.binding.root.setOnClickListener {
            val keDetailuser = Intent(holder.itemView.context, DetailUserActivity::class.java)
            keDetailuser.putExtra( "username", user.login)
            keDetailuser.putExtra( "id", user.id)
            keDetailuser.putExtra( "name", user.name)
            holder.itemView.context.startActivity(keDetailuser)
        }

        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ViewHolder(val binding: ItemUserBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(user: ItemsItem){
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.tvItemName.setText(user.login)
            binding.tvItemId.setText(user.id.toString())
        }
    }
}