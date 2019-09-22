package c.example.architecture

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : AppCompatActivity() {

    private lateinit var fragment: ArFragment
    private lateinit var modelUri: Uri
    private lateinit var modelRenderable: ModelRenderable
    private var modelIsLoaded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        modelIsLoaded = false
        fragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment

        //modelUri = Uri.parse("model01.sfb")

        getModel()
        /*ModelRenderable.builder()
            .setSource(fragment.context, modelUri)
            .build()
            .thenAccept { modelRenderable = it }*/

        fragment.arSceneView.scene.addOnUpdateListener { frameTime ->

            onUpdate(frameTime)
        }


    }

    private fun getModel() {
        val modelID: Int? = intent.getIntExtra("modelId", 0)
        Toast.makeText(this, modelID.toString(), Toast.LENGTH_SHORT).show()

        if (modelID == 0) {
            buildModel(Uri.parse("model01.sfb"))
            btnVoteYes.isVisible = false
            btnVoteNo.isVisible = false
        }
        if (modelID == 1) {
            buildModel(Uri.parse("model01.sfb"))
        }
        if (modelID == 2) {
            buildModel(Uri.parse("Samba_Dancing.sfb"))
        }
        if (modelID == 3) {
            buildModel(Uri.parse("lion.sfb"))
        }
        if (modelID == 4) {
            // swap to that asset
        }
        if (modelID == 5) {
            // swap to that asset
        }
        if (modelID == 6) {
            // swap to that asset
        }
        if (modelID == 7) {
            // swap to that asset
        }
        if (modelID == 8) {
            // swap to that asset
        }
    }

    private fun onUpdate(frameTime: FrameTime?) {
        if (!modelIsLoaded) {
            fragment.onUpdate(frameTime)
            val arFrame = fragment.arSceneView.arFrame
            if (arFrame == null || arFrame?.camera.trackingState != TrackingState.TRACKING) return
            val updatedAImages = arFrame.getUpdatedTrackables(AugmentedImage::class.java)
            updatedAImages.forEach {
                when (it.trackingState) {
                    TrackingState.PAUSED -> {
                    }
                    TrackingState.STOPPED -> {
                    }
                    TrackingState.TRACKING -> if (it.anchors.isEmpty()) loadModel(it)
                }
            }
        }
    }

    private fun loadModel(it: AugmentedImage) {
        imgv_fit_to_scan.visibility = View.GONE
        val pose = it.centerPose
        val anchor = it.createAnchor(pose)
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(fragment.arSceneView.scene)
        val modelNode = TransformableNode(fragment.transformationSystem)
        modelNode.setParent(anchorNode)
        when (it.name) {
            "statue" -> {
                modelNode.renderable = modelRenderable
                modelIsLoaded = !modelIsLoaded
            }
        }
    }

    private fun buildModel(modelUri: Uri) {
        ModelRenderable.builder()
            .setSource(fragment.context, modelUri)
            .build()
            .thenAccept { modelRenderable = it }
    }
}
