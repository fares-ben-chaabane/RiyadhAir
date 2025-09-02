package fr.benchaabane.riyadhair.konsist

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class ViewModelNamingTest {

    @Test
    fun `classes extending ViewModel should have ViewModel suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(ViewModel::class)
            .assertTrue { it.name.endsWith("ViewModel") }
    }

    @Test
    fun `Every 'ViewModel' public property has 'Flow' type`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(ViewModel::class)
            .properties()
            .assertTrue {
                it.hasPublicOrDefaultModifier && it.hasType { type -> type.name == "kotlinx.coroutines.flow.StateFlow"
                    || type.name == "kotlinx.coroutines.flow.SharedFlow"}
            }
    }

}


