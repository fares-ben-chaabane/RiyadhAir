package fr.benchaabane.riyadhair.konsist

import com.lemonappdev.konsist.api.Konsist
import org.junit.Test

class BasicKonsistTest {
    
    @Test
    fun `project should have source files`() {
        val files = Konsist.scopeFromProject().files
        assert(files.isNotEmpty())
    }
}


