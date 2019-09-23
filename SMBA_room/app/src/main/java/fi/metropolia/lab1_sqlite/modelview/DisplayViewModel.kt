package fi.metropolia.lab1_sqlite.modelview

import android.app.Application
import androidx.lifecycle.MutableLiveData
import fi.metropolia.lab1_sqlite.model.UserContact
import fi.metropolia.lab1_sqlite.model.UserDatabase
import kotlinx.coroutines.launch

class DisplayViewModel(application: Application) : BaseViewModel(application) {
    val userContactList = MutableLiveData<List<UserContact>>()

    fun fetchFromDatabase() {
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            val userContactList = dao.getUserContact()
            userContactRetreived(userContactList)
        }
    }

    private fun userContactRetreived(userContact: List<UserContact>) {
        println("fetchFromDatabase")
        println(userContactList)
        userContactList.value = userContact
    }
}