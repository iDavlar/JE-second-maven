package daniil.ardiukov;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        try {
            String path = App.getDataBasePath();
            Jakarta jakarta_1 = App.createJakarta();
            jakarta_1.writeToJson(path);

            Jakarta jakarta_2 = Jakarta.readFromJson(path);

            assertEquals(jakarta_1, jakarta_2);
        } catch (URISyntaxException | IOException e) {
            fail("DB not found");
        }
    }
}
