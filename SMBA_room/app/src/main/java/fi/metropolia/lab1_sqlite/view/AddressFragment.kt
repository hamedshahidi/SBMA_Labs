package fi.metropolia.lab1_sqlite.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import fi.metropolia.lab1_sqlite.R
import fi.metropolia.lab1_sqlite.model.Address
import fi.metropolia.lab1_sqlite.model.User
import fi.metropolia.lab1_sqlite.model.UserDatabase
import kotlinx.android.synthetic.main.fragment_address.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddressFragment : Fragment(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            userName = AddressFragmentArgs.fromBundle(it).userName
        }

        addressBtn.setOnClickListener {
            if (addressInput.text.isNotEmpty()) {
                val address = addressInput.text.toString()
                storeUserLocally(userName, address, it)


            }
        }
    }

    private fun storeUserLocally(userName: String, address: String, view: View) {
        val userDao = UserDatabase(context!!).userDao()
        val addressDao = UserDatabase(context!!).addressDao()
        launch {
            val user = User(userName)
            val userId = userDao.insertUser(user)
            val userAddress = Address(address, userId)
            addressDao.insertAddress(userAddress)
            navigateToDisplay(view)
        }
    }

    private fun navigateToDisplay(view: View) {
        val action = AddressFragmentDirections.actionAddressFragmentToDisplayFragment()
        Navigation.findNavController(view).navigate(action)
    }
}
