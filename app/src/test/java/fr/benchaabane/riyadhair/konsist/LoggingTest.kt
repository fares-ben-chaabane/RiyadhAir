package fr.benchaabane.riyadhair.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertFalse
import org.junit.Test

class LoggingTest {

    @Test
    fun `no class should use Android util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.hasImport { import -> import.name == "android.util.Log" } }
    }

}


