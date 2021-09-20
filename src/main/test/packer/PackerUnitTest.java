package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.FileFormatException;
import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;
import com.mobiquityinc.service.reader.KnapsackSimpleFileReader;
import com.mobiquityinc.service.solver.KnapsackEfficientRecursiveSolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
/**
 * @author AmranHossain on 21/9/2564
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Packer.class)
public class PackerUnitTest {

    private KnapsackSimpleFileReader reader;
    private KnapsackEfficientRecursiveSolver solver;

    @Before
    public void setup() throws Exception {
        reader = Mockito.mock(KnapsackSimpleFileReader.class);
        PowerMockito.whenNew(KnapsackSimpleFileReader.class).withNoArguments().thenReturn(reader);

        solver = Mockito.mock(KnapsackEfficientRecursiveSolver.class);
        PowerMockito.whenNew(KnapsackEfficientRecursiveSolver.class).withNoArguments().thenReturn(solver);
    }

    @Test
    public void testConcatenation() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        KnapsackProblem anyProblem = KnapsackProblem.builder().weightLimit(BigDecimal.ZERO).items(Collections.emptyList()).build();
        PowerMockito.doReturn(Stream.of(anyProblem, anyProblem)).when(reader).read(any());
        PowerMockito.doReturn(KnapsackResult.empty()).when(solver).solve(any());

        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("anyPath");

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals("-\r\n-", actual);
    }

    @Test
    public void testNothingToRead() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        PowerMockito.doReturn(Stream.of()).when(reader).read(any());

        // Execution phase -------------------------------------------------------------
        String actual = Packer.pack("anyPath");

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals("-", actual);
    }

    @Test(expected = APIException.class)
    public void testFileFormatExceptionTranslation() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        PowerMockito.doThrow(new FileFormatException()).when(reader).read(any());

        // Execution phase -------------------------------------------------------------
        Packer.pack("anyPath");
    }

    @Test(expected = APIException.class)
    public void testIllegalArgumentExceptionTranslation() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        PowerMockito.doThrow(new IllegalArgumentException()).when(reader).read(any());

        // Execution phase -------------------------------------------------------------
        Packer.pack("anyPath");
    }

}
