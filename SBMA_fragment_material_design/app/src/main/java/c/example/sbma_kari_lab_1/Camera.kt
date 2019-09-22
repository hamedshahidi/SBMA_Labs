package c.example.sbma_kari_lab_1

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.frag03.*
import java.io.File


class Camera() : AppCompatActivity() {

    lateinit var fManager: FragmentManager
    lateinit var fTransaction: FragmentTransaction

    val REQUEST_IMAGE_CAPTURE = 99
    var mCurrentPhotoPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE
            && resultCode == Activity.RESULT_OK
        ) { savePhoto() }
    }

    // for saving high resolution
    fun takePhoto(){
        //Create a file into app's external storage area
        val fileName = "temp_photo"
        val imagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var imageFile: File = File.createTempFile(fileName, ".jpg", imagePath)

        //Create Content URI
        val photoURI: Uri = FileProvider.getUriForFile(
            this, "c.example.sbma_kari_camera_1", imageFile)

        //Save Photo into File
        mCurrentPhotoPath = imageFile.absolutePath

        val myIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (myIntent.resolveActivity(packageManager) != null){
            myIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(myIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun savePhoto(){
        val imageBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
        val frag = Frag03()


        fManager = supportFragmentManager
        fTransaction = fManager.beginTransaction()
        frag.imageView.setImageBitmap(imageBitmap)
        fTransaction.add(R.id.fContainer, frag)
        fTransaction.commit()



    }




}