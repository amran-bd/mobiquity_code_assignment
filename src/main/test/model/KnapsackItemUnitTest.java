package com.mobiquityinc.model;

import com.mobiquityinc.model.KnapsackItem.KnapsackItemBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author AmranHossain on 21/9/2564
 */
public class KnapsackItemUnitTest {

    @Test
    public void testHappyPath() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackItemBuilder builder = KnapsackItem.builder().index(1).cost(10).weight(BigDecimal.valueOf(100));

        // Execution phase -------------------------------------------------------------
        KnapsackItem result = builder.build();

        // Assertion phase -------------------------------------------------------------
        Assert.assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaximumItemWeight() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackItemBuilder builder = KnapsackItem.builder().index(1).cost(10).weight(BigDecimal.valueOf(101));

        // Execution phase -------------------------------------------------------------
        builder.build();
    }

}
