package fr.benchaabane.riyadhair.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.Test

class CleanArchitectureTest {

    @Test
    fun `clean architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                // Define layers
                val packagePrefix = "fr.benchaabane.riyadhair"
                val domain = Layer("domain", "$packagePrefix..domain..")
                val presentation = Layer("presentation", "$packagePrefix..presentation..")
                val data = Layer("data", "$packagePrefix..data..")

                // Define architecture assertions
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

}


