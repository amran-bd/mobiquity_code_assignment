package com.mobiquityinc.service.solver;

import com.mobiquityinc.model.KnapsackItem;
import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author AmranHossain on 21/9/2564
 */
public class KnapsackEfficientRecursiveSolverUnitTest {

    private KnapsackEfficientRecursiveSolver underTest = new KnapsackEfficientRecursiveSolver();

    @Test
    public void testHappyPath() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackItem firstItem = KnapsackItem.builder().index(1).weight(BigDecimal.valueOf(53.38)).cost(45).build();
        KnapsackItem secondItem = KnapsackItem.builder().index(2).weight(BigDecimal.valueOf(88.62)).cost(98).build();
        KnapsackProblem problem = KnapsackProblem.builder().weightLimit(BigDecimal.valueOf(81)).items(Arrays.asList(firstItem, secondItem)).build();

        // Execution phase -------------------------------------------------------------
        KnapsackResult result = underTest.solve(problem);
        List<KnapsackItem> resultItems = result.items();

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals(1, resultItems.size());
        Assert.assertEquals(firstItem, resultItems.get(0));
    }

    @Test
    public void testLessWeight() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackItem firstItem = KnapsackItem.builder().index(1).weight(BigDecimal.valueOf(53.38)).cost(45).build();
        KnapsackItem secondItem = KnapsackItem.builder().index(2).weight(BigDecimal.valueOf(38.62)).cost(45).build();
        KnapsackProblem problem = KnapsackProblem.builder().weightLimit(BigDecimal.valueOf(81)).items(Arrays.asList(firstItem, secondItem)).build();

        // Execution phase -------------------------------------------------------------
        KnapsackResult result = underTest.solve(problem);
        List<KnapsackItem> resultItems = result.items();

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals(1, resultItems.size());
        Assert.assertEquals(secondItem, resultItems.get(0));
    }

    @Test
    public void testMoreCost() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackItem firstItem = KnapsackItem.builder().index(1).weight(BigDecimal.valueOf(53.38)).cost(45).build();
        KnapsackItem secondItem = KnapsackItem.builder().index(2).weight(BigDecimal.valueOf(53.38)).cost(46).build();
        KnapsackProblem problem = KnapsackProblem.builder().weightLimit(BigDecimal.valueOf(81)).items(Arrays.asList(firstItem, secondItem)).build();

        // Execution phase -------------------------------------------------------------
        KnapsackResult result = underTest.solve(problem);
        List<KnapsackItem> resultItems = result.items();

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals(1, resultItems.size());
        Assert.assertEquals(secondItem, resultItems.get(0));
    }

    @Test
    public void testNotMatched() {
        // Arrangement phase -----------------------------------------------------------
        KnapsackItem firstItem = KnapsackItem.builder().index(1).weight(BigDecimal.valueOf(53.38)).cost(45).build();
        KnapsackItem secondItem = KnapsackItem.builder().index(2).weight(BigDecimal.valueOf(88.62)).cost(98).build();
        KnapsackProblem problem = KnapsackProblem.builder().weightLimit(BigDecimal.valueOf(11)).items(Arrays.asList(firstItem, secondItem)).build();

        // Execution phase -------------------------------------------------------------
        KnapsackResult result = underTest.solve(problem);
        List<KnapsackItem> resultItems = result.items();

        // Assertion phase -------------------------------------------------------------
        Assert.assertEquals(0, resultItems.size());
    }

}
