package fr.benchaabane.riyadhair.konsist

import com.lemonappdev.konsist.api.Konsist
import org.junit.Test

class PackagesTest {
    
    @Test
    fun `project should have packages`() {
        val packages = Konsist.scopeFromProject().packages
        assert(packages.isNotEmpty())
    }
    
    @Test
    fun `project should have domain package`() {
        val domainPackages = Konsist.scopeFromProject().packages.filter { 
            it.name.contains("domain") 
        }
        assert(domainPackages.isNotEmpty())
    }
    
    @Test
    fun `project should have data package`() {
        val dataPackages = Konsist.scopeFromProject().packages.filter { 
            it.name.contains("data") 
        }
        assert(dataPackages.isNotEmpty())
    }
    
    @Test
    fun `project should have presentation package`() {
        val presentationPackages = Konsist.scopeFromProject().packages.filter { 
            it.name.contains("presentation") 
        }
        assert(presentationPackages.isNotEmpty())
    }
}


