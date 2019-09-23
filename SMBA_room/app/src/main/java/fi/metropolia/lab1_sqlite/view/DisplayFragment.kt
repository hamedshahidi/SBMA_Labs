package fi.metropolia.lab1_sqlite.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import fi.metropolia.lab1_sqlite.R
import fi.metropolia.lab1_sqlite.model.UserContact
import fi.metropolia.lab1_sqlite.modelview.DisplayViewModel
import kotlinx.android.synthetic.main.fragment_display.*

class DisplayFragment : Fragment() {

    private val userContactArray = arrayListOf<UserContact>()
    private val userListAdapter = UserListAdapter(userContactArray)
    private lateinit var viewModel: DisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DisplayViewModel::class.java)
        viewModel.fetchFromDatabase()

        userList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userContactList.observe(this, Observer {userContactList ->
            userContactList?.let {
                println("observeViewModel")
                println(userContactList)
                userListAdapter.updateUserList(userContactList)
            }
        })
    }
}
