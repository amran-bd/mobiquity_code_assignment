package com.mobiquity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author AmranHossain on 19/9/2564
 */
@Value
@Accessors(fluent = true)
@Setter@Getter
public class KnapsackProblemDTO implements Serializable {

    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(100);

    private BigDecimal weightLimit;
    private List<KnapsackItemDTO> items;

    @Builder
    public KnapsackProblemDTO(BigDecimal weightLimit, List<KnapsackItemDTO> items) {
        this.items = items;
        this.weightLimit = Optional.of(weightLimit)
                .filter(w -> MAX_WEIGHT.compareTo(w) >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Maximum package wight is 100!"));
    }
}
