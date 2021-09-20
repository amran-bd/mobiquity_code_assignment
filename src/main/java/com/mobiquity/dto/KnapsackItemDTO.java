package com.mobiquity.dto;

import com.mobiquity.exception.FormatException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author AmranHossain on 19/9/2564
 */
@Value
@Accessors(fluent = true)
@Setter@Getter
public class KnapsackItemDTO implements Serializable {

    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(100);
    private Integer index;
    private BigDecimal weight;
    private Integer cost;

    @Builder
    public KnapsackItemDTO(Integer index, BigDecimal weight, Integer cost) {
        this.index = index;
        this.cost = cost;
        this.weight = Optional.of(weight)
                .filter(w -> MAX_WEIGHT.compareTo(w) >= 0)
                .orElseThrow(() -> new FormatException(String.format("Index '%d': Maximum wight is 100!", index)));
    }


}
