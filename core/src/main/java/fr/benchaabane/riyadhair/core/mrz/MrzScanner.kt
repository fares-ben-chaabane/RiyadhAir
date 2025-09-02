package fr.benchaabane.riyadhair.core.mrz

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * A composable that provides MRZ (Machine Readable Zone) scanning functionality.
 *
 * This component integrates CameraX to capture and analyze images for MRZ codes,
 * commonly found on passports, visas, and other travel documents. It automatically
 * sets up the camera preview and image analysis pipeline.
 *
 * **Features:**
 * - Real-time camera preview using CameraX
 * - Automatic MRZ code detection and parsing
 * - Lifecycle-aware camera management
 * - Back camera selection by default
 *
 * **Camera Setup:**
 * - **Preview**: Shows live camera feed
 * - **Image Analysis**: Processes frames for MRZ detection
 * - **Camera Provider**: Manages camera lifecycle and permissions
 *
 * **Usage:**
 * ```kotlin
 * @Composable
 * fun DocumentScannerScreen() {
 *     var mrzCode by remember { mutableStateOf<String?>(null) }
 *     
 *     MrzScanner(
 *         modifier = Modifier.fillMaxSize(),
 *         onMrz = { code ->
 *             mrzCode = code
 *             // Handle detected MRZ code
 *         }
 *     )
 *     
 *     mrzCode?.let { code ->
 *         Text("Detected MRZ: $code")
 *     }
 * }
 * ```
 *
 * **Permissions Required:**
 * - `android.permission.CAMERA` - Must be granted before use
 *
 * **Lifecycle Management:**
 * - Automatically binds/unbinds camera based on composition lifecycle
 * - Handles camera provider lifecycle events
 * - Manages camera resources efficiently
 *
 * @param modifier Modifier to apply to the scanner view
 * @param onMrz Callback invoked when an MRZ code is detected, providing the parsed string
 */
@Composable
fun MrzScanner(
    modifier: Modifier = Modifier,
    onMrz: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            PreviewView(ctx).apply {
                // CameraX setup would go here
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = surfaceProvider
                }
                val imageAnalysis = ImageAnalysis.Builder().build().apply {
                    setAnalyzer(ContextCompat.getMainExecutor(context), MrzAnalyzer(onMrz))
                }
                
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageAnalysis
                    )
                }, ContextCompat.getMainExecutor(context))
            }
        }
    )
}
