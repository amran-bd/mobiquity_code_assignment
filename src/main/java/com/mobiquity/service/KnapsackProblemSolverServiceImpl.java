package com.mobiquity.service;

import com.mobiquity.dto.KnapsackItemDTO;
import com.mobiquity.dto.KnapsackProblemDTO;
import com.mobiquity.dto.ResultDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author AmranHossain on 19/9/2564
 */
public class KnapsackProblemSolverServiceImpl implements KnapsackProblemSolverService{

    @Override
    public ResultDTO solution(KnapsackProblemDTO problem) {
        final BigDecimal weightLimit = problem.weightLimit();
        final List<KnapsackItemDTO> allItems = problem.items();
        final int itemsSize = allItems.size();
        if ((weightLimit.compareTo(BigDecimal.ZERO) <= 0) || (itemsSize <= 0)) {
            return ResultDTO.empty();
        }

        final KnapsackItemDTO currentItem = allItems.get(itemsSize - 1);
        final List<KnapsackItemDTO> previousItems = allItems.subList(0, itemsSize - 1);

        KnapsackProblemDTO previousProblem = KnapsackProblemDTO.builder()
                .weightLimit(weightLimit)
                .items(previousItems)
                .build();
        KnapsackProblemDTO remainedProblem = KnapsackProblemDTO.builder()
                .weightLimit(weightLimit.subtract(currentItem.weight()))
                .items(previousItems)
                .build();

        final ResultDTO previousResult = solution(previousProblem);
        final ResultDTO currentResult = solution(remainedProblem).clone().addItem(currentItem);

        return max(previousResult, currentResult, weightLimit);
    }

    private ResultDTO max(ResultDTO first, ResultDTO second, BigDecimal weightLimit) {
        if (second.weight().compareTo(weightLimit) > 0) {
            return first.weight().compareTo(weightLimit) > 0 ? ResultDTO.empty() : first;
        }
        return (first.weight().compareTo(weightLimit) > 0) || (first.compareTo(second) < 0) ? second : first;
    }
}
