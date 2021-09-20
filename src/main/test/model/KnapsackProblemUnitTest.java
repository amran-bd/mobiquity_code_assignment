package com.mobiquityinc.model;

import com.mobiquityinc.model.KnapsackProblem.KnapsackProblemBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * @author AmranHossain on 21/9/2564
 */
public class KnapsackProblemUnitTest {

    @Test
    public void testHappyPath() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackProblemBuilder builder = KnapsackProblem.builder().weightLimit(BigDecimal.valueOf(100)).items(Collections.emptyList());

        // Execution phase -------------------------------------------------------------
        KnapsackProblem result = builder.build();

        // Assertion phase -------------------------------------------------------------
        Assert.assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaximumPackageWeight() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackProblemBuilder builder = KnapsackProblem.builder().weightLimit(BigDecimal.valueOf(101)).items(Collections.emptyList());

        // Execution phase -------------------------------------------------------------
        builder.build();
    }

}
