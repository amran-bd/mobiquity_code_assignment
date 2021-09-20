package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author AmranHossain on 21/9/2564
 */
public class PackerIntegrationTest {

    @Test
    public void testHappyPath() throws Exception {
        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("src/test/resources/example_input.txt");

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals("4\r\n-\r\n2, 7\r\n8, 9", actual);
    }

    @Test
    public void testEmptyLines() throws Exception {
        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("src/test/resources/sample-files/empty-lines.txt");

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals("4\r\n-\r\n2, 7\r\n8, 9", actual);
    }

    @Test
    public void testEmptyFile() throws Exception {
        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("src/test/resources/sample-files/empty-file.txt");

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals("-", actual);
    }

    @Test(expected = APIException.class)
    public void testWrongPath() throws Exception {
        // Execution phase -------------------------------------------------------------
        Packer.pack("wrongPath");
    }

    @Test(expected = APIException.class)
    public void testInvalidFileFormat() throws Exception {
        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("src/test/resources/sample-files/invalid-format.txt");
    }

    @Test(expected = APIException.class)
    public void testIllegalPackageWight() throws Exception {
        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("src/test/resources/sample-files/illegal-package-wight.txt");
    }

    @Test(expected = APIException.class)
    public void testIllegalItemWight() throws Exception {
        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("src/test/resources/sample-files/illegal-item-wight.txt");
    }

}
