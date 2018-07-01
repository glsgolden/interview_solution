package interivew_solution;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.reis.tiaa.executor.ProductBuilder;

public class ProductBuilderTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void testProductLine1() {
		ProductBuilder atc = new ProductBuilder(0, 0, 0);

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Assembly time must be greater than 0.");
		atc.startWork();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Number of machines and bolts must be greater than 0.");
		atc = new ProductBuilder(0, 0, 1);
		atc.startWork();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Number of machines and bolts must be greater than 0.");
		atc = new ProductBuilder(0, 1, 1);
		atc.startWork();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Number of bolts must be double of number of machines.");
		atc = new ProductBuilder(1, 1, 1);
		atc.startWork();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Number of bolts must be double of number of machines.");
		atc = new ProductBuilder(1, 3, 1);
		atc.startWork();
	}

	@Test
	public void testProductLine2() {
		final ProductBuilder atc = new ProductBuilder(1, 2, 40);
		atc.startWork();
		Assert.assertEquals(1, atc.getTotalProduct());
		Assert.assertEquals(40, atc.getTotalTimeTaken());
	}

	@Test
	public void testProductLine3() {
		final ProductBuilder atc = new ProductBuilder(2, 4, 45);
		atc.startWork();
		Assert.assertEquals(2, atc.getTotalProduct());
		Assert.assertEquals(45, atc.getTotalTimeTaken());

	}

	@Test
	public void testProductLine4() {
		final ProductBuilder atc = new ProductBuilder(3, 6, 49);
		atc.startWork();
		
		Assert.assertEquals(3, atc.getTotalProduct());
		Assert.assertEquals(49, atc.getTotalTimeTaken());

	}
}
