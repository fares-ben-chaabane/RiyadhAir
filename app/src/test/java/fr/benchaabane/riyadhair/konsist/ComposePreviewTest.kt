package fr.benchaabane.riyadhair.konsist

import androidx.compose.ui.tooling.preview.Preview
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class ComposePreviewTest {

    @Test
    fun `All JetPack Compose previews contain 'Preview' in method name`() {
        Konsist
            .scopeFromProject()
            .functions()
            .withAnnotationOf(Preview::class)
            .assertTrue {
                it.hasNameContaining("Preview")
            }
    }

}


