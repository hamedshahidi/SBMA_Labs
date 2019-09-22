package c.example.sbma_patrik_storage_2


import android.os.Bundle
import android.os.Environment.*
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_write_storage.*
import java.io.File

const val TAG = "DBG"
const val fileName = "file1.txt"

class WriteStorage : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_write_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSaveToFile.setOnClickListener {
            if (getExternalStorageState() == MEDIA_MOUNTED){
                val file = File(getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS), fileName)
//                val count = file.readText().length
                file.appendText("${et_inputContent.text}\n")

  //              if (et_inputContent.text.isNotEmpty() && file.readText().length > count){
                    tv_saveStatus.text = getString(R.string.str_saveStatus)
  //              }
                et_inputContent.text?.clear()
            }
        }




        // unbder development
        et_inputContent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                tv_saveStatus.text = ""
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}
