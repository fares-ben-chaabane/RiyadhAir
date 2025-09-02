package fr.benchaabane.riyadhair.core.mrz

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

/**
 * Analyzes camera images to detect and extract MRZ (Machine Readable Zone) codes.
 *
 * This class implements CameraX's ImageAnalysis.Analyzer interface to process
 * camera frames in real-time. It uses ML Kit's Text Recognition API to identify
 * text in images and applies simple heuristics to detect MRZ codes.
 *
 * **MRZ Detection Strategy:**
 * - Uses ML Kit Text Recognition for OCR (Optical Character Recognition)
 * - Applies simple pattern matching for MRZ identification
 * - Looks for the "<<" sequence which is characteristic of MRZ codes
 *
 * **Image Processing Pipeline:**
 * 1. Receives ImageProxy from CameraX
 * 2. Converts to ML Kit InputImage format
 * 3. Processes with Text Recognition API
 * 4. Applies MRZ detection logic
 * 5. Calls callback with detected MRZ code
 *
 * **Usage:**
 * ```kotlin
 * val analyzer = MrzAnalyzer { mrzCode ->
 *     // Handle detected MRZ code
 *     Log.d("MRZ", "Detected: $mrzCode")
 * }
 *
 * imageAnalysis.setAnalyzer(
 *     ContextCompat.getMainExecutor(context),
 *     analyzer
 * )
 * ```
 *
 * **Performance Considerations:**
 * - Processes frames asynchronously
 * - Automatically closes ImageProxy to prevent memory leaks
 * - Uses ML Kit's optimized text recognition
 *
 * **Limitations:**
 * - Simple pattern matching may produce false positives
 * - Requires good image quality and lighting
 * - Text recognition accuracy depends on image clarity
 *
 * @param onMrz Callback function invoked when an MRZ code is detected
 */
class MrzAnalyzer(
    private val onMrz: (String) -> Unit
) : ImageAnalysis.Analyzer {

    /**
     * ML Kit Text Recognition client for OCR processing.
     *
     * This recognizer is configured with Latin script options,
     * which is suitable for most international travel documents.
     * It's initialized once and reused for all image analysis.
     */
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    /**
     * Analyzes a camera image to detect MRZ codes.
     *
     * This method is called by CameraX for each captured frame.
     * It processes the image using ML Kit Text Recognition and
     * applies MRZ detection logic to the extracted text.
     *
     * **Processing Steps:**
     * 1. Extracts MediaImage from ImageProxy
     * 2. Creates InputImage for ML Kit processing
     * 3. Runs text recognition asynchronously
     * 4. Applies MRZ detection pattern matching
     * 5. Invokes callback if MRZ is detected
     * 6. Ensures ImageProxy is properly closed
     *
     * **Error Handling:**
     * - Gracefully handles null MediaImage
     * - Ensures ImageProxy closure in all code paths
     * - Continues processing even if recognition fails
     *
     * @param image The ImageProxy containing the camera frame to analyze
     */
    @ExperimentalGetImage
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null) {
            image.close()
            return
        }
        
        val inputImage = InputImage.fromMediaImage(mediaImage!!, image.imageInfo.rotationDegrees)
        
        recognizer.process(inputImage).addOnSuccessListener { visionText ->
            val extractedText = visionText.text
            if (extractedText.contains("<<")) {
                // Simple MRZ detection - contains << which is typical in MRZ
                onMrz(extractedText)
            }
        }.addOnCompleteListener {
            image.close()
        }
    }
}
