package fr.benchaabane.riyadhair.konsist

import com.lemonappdev.konsist.api.Konsist
import org.junit.Test

class UseCaseTest {

    @Test
    fun `UseCase classes should have invoke function`() {
        val useCaseClasses = Konsist
            .scopeFromProject()
            .classes()
            .filter { it.name.endsWith("UseCase") }

        val useCasesWithInvoke = useCaseClasses.filter { useCase ->
            useCase.functions().all { function ->
                function.name == "invoke"
            }
        }

        assert(useCasesWithInvoke.isNotEmpty()) {
            "UseCase classes should have invoke function"
        }
    }
}


