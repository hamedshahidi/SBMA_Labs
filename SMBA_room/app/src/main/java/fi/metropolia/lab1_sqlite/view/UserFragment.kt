package fi.metropolia.lab1_sqlite.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import fi.metropolia.lab1_sqlite.R
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userBtn.setOnClickListener {
            if (userInput.text.isNotEmpty()) {
                navigateToAddress(userInput.text.toString(), it)
            }
        }
    }

    private fun navigateToAddress(userName: String, view: View) {
        val action = UserFragmentDirections.actionUserFragmentToAddressFragment()
        action.userName = userName
        Navigation.findNavController(view).navigate(action)
    }
}
