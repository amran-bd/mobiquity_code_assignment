package com.mobiquityinc.service.reader;

import com.mobiquityinc.exception.FileFormatException;
import com.mobiquityinc.model.KnapsackProblem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author AmranHossain on 21/9/2564
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(KnapsackSimpleFileReader.class)
@SuppressWarnings("ResultOfMethodCallIgnored")
public class KnapsackSimpleFileReaderUnitTest {

    private KnapsackSimpleFileReader underTest = new KnapsackSimpleFileReader();

    @Before
    public void setup() {
        PowerMockito.mockStatic(Files.class);
    }

    @Test
    public void testHappyPath() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        String line = "58 : (1,53.38,€45) (2,88.62,€98)";
        PowerMockito.when(Files.lines(any())).thenReturn(Stream.of(line));

        // Execution phase -------------------------------------------------------------
        List<KnapsackProblem> problems = underTest.read("anyPath").collect(Collectors.toList());

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals(1, problems.size());
        KnapsackProblem problem = problems.get(0);

        Assert.assertEquals(BigDecimal.valueOf(58), problem.weightLimit());
        Assert.assertEquals(2, problem.items().size());
    }

    @Test
    public void testSkipIncorrectItem() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        String line = "58 : (1,53.38,€45) (2,88.62,)";
        PowerMockito.when(Files.lines(any())).thenReturn(Stream.of(line));

        // Execution phase -------------------------------------------------------------
        List<KnapsackProblem> problems = underTest.read("anyPath").collect(Collectors.toList());

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals(1, problems.size());
        KnapsackProblem problem = problems.get(0);

        Assert.assertEquals(BigDecimal.valueOf(58), problem.weightLimit());
        Assert.assertEquals(1, problem.items().size());
    }

    @Test(expected = FileFormatException.class)
    public void testInvalidLineFormat() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        String line = "58 :: (1,53.38,€45) (2,88.62,€98)";
        PowerMockito.when(Files.lines(any())).thenReturn(Stream.of(line));

        // Execution phase -------------------------------------------------------------
        underTest.read("anyPath").count();
    }

    @Test(expected = FileFormatException.class)
    public void testLineWithoutWeightLimit() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        String line = ": (1,53.38,€45) (2,88.62,€98)";
        PowerMockito.when(Files.lines(any())).thenReturn(Stream.of(line));

        // Execution phase -------------------------------------------------------------
        underTest.read("anyPath").count();
    }

    @Test(expected = FileFormatException.class)
    public void testLineWithoutItem() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        String line = "58: ";
        PowerMockito.when(Files.lines(any())).thenReturn(Stream.of(line));

        // Execution phase -------------------------------------------------------------
        underTest.read("anyPath").count();
    }

    @Test(expected = FileFormatException.class)
    public void testInvalidItemFormat() throws Exception {
        // Arrangement phase -----------------------------------------------------------
        String line = "58 : (1,53.38)";
        PowerMockito.when(Files.lines(any())).thenReturn(Stream.of(line));

        // Execution phase -------------------------------------------------------------
        underTest.read("anyPath").count();
    }

}
