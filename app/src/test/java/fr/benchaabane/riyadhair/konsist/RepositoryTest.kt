package fr.benchaabane.riyadhair.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class RepositoryTest {

    @Test
    fun `'Repository' classes should reside in 'repository' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .assertTrue { it.resideInPackage("..repositories..") }
    }
}


