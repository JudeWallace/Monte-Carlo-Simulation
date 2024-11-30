import com.mcsimulation.Main;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTests {

    @Test
    public void testMonteCarloSimulation(){
        final int N = 100000;
        final int S = 100;
        Main.monteCarloSimulation(S, N);
        assertEquals(N, Main.getFinalPrices().values().stream().mapToInt(x -> x).sum());

    }
}
