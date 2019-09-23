package fi.metropolia.lab1_sqlite.view

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.lab1_sqlite.R
import fi.metropolia.lab1_sqlite.model.UserContact
import kotlinx.android.synthetic.main.user_item.view.*

class UserListAdapter(private val userContactList: ArrayList<UserContact>):
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    fun updateUserList(newUserContactList: List<UserContact>) {
        userContactList.clear()
        userContactList.addAll(newUserContactList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item,parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userContactList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        println("onBindViewHolder")
        println(userContactList)
        val userContact = userContactList[position]
        userContact.user?.let {
            holder.view.userNameTxt.text = it.userName
        }
        userContact.addressList?.let { it ->
            if(it.isNotEmpty()) {
                var address = ""
                it.forEach{
                    address += "${it.address}\n"
                }
                holder.view.addressTxt.text = address
            } else {
                holder.view.addressTxt.text = holder.view.context.resources.getString(R.string.no_address)
            }

        }
    }

    class UserViewHolder(var view: View):RecyclerView.ViewHolder(view)
}