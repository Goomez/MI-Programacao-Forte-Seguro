package br.uefs.ecomp.forteseguro;
import br.uefs.ecomp.forteseguro.controller.ControllerTest;
import br.uefs.ecomp.forteseguro.util.GrafoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   ControllerTest.class,
    GrafoTest.class,
})

public class AllTestes {
    
}
