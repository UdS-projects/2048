package ttfe.publictests;

import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Test;

import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

public class TTFEFactoryTests {

	@Test
	public void testSimulatorNonNull() {
		SimulatorInterface game = TTFEFactory.createSimulator(4, 4, new Random(0));
		assertNotNull(game);
	}

}

