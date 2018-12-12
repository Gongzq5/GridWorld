// // CaculatorTest.java

// import Cal.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class CaculatorTest {
	Calculator entity = new Calculator();

	@Test
	public void AddTest() {
		int c = entity.Add(2,4);
		assertEquals(8, c);
	}

}