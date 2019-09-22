package c.example.sbma_patrik_storage_2


import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_read_storage.*
import java.io.File
import java.io.PrintWriter
import java.time.LocalDate

class ReadStorage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_read_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName )

        btnReadFile.setOnClickListener {
            showFileContent(file)
        }

        btnClearFile.setOnClickListener {
            val writer = PrintWriter(file)
            writer.print("")
            writer.close()
            showFileContent(file)
        }

        btnList.setOnClickListener {
            listFolders()
        }

    }
    fun showFileContent(vfile: File){
            tv_fileContent.text = vfile.readText()
        }

    fun listFolders(){
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            val file = File(Environment.getExternalStorageDirectory().absolutePath)
            val files: Array<File> = (file.listFiles())
            var txt = ""
            for (file in files) {
                txt += "${file.absolutePath}\n"
            }
            tv_fileContent.text = txt
        }
    }
}
