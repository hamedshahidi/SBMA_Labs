package c.example.architecture

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment

class AImageFragment: ArFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        setupAugmentedImageDatabase(config, session)
        return config
    }

    private fun setupAugmentedImageDatabase(config: Config, session: Session?) {
        val augmentedImageDatabase: AugmentedImageDatabase
        val assetManager = context!!.assets
        val inputStream01 = assetManager.open("statue_spot.jpg")
        val augmentedImageBitmap1 = BitmapFactory.decodeStream(inputStream01)
        augmentedImageDatabase = AugmentedImageDatabase(session)
        augmentedImageDatabase.addImage("statue", augmentedImageBitmap1)
        config.augmentedImageDatabase = augmentedImageDatabase
    }
}