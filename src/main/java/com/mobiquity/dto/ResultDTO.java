package com.mobiquity.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AmranHossain on 19/9/2564
 */
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
public class ResultDTO implements Cloneable, Comparable<ResultDTO> {

    private static final ResultDTO EMPTY = new ResultDTO();

    private BigDecimal weight = BigDecimal.ZERO;
    private Integer cost = 0;
    private List<KnapsackItemDTO> items = new ArrayList<>();

    private ResultDTO() {
    }

    public static ResultDTO empty() {
        return EMPTY;
    }

    public ResultDTO addItem(KnapsackItemDTO item) {
        this.weight = this.weight.add(item.weight());
        this.cost += item.cost();
        this.items.add(item);
        return this;
    }

    @Override
    public ResultDTO clone() {
        ResultDTO clone = new ResultDTO();
        clone.weight = this.weight;
        clone.cost = this.cost;
        clone.items = new ArrayList<>(this.items);
        return clone;
    }

    @Override
    public int compareTo(ResultDTO other) {
        if (other == null) {
            return 1;
        }
        if (this.cost.compareTo(other.cost) == 0) {
            return other.weight.compareTo(this.weight);
        }
        return this.cost.compareTo(other.cost);
    }

    @Override
    public String toString() {
        return items.stream()
                .map(KnapsackItemDTO::index)
                .map(Object::toString)
                .reduce((first, second) -> String.format("%s, %s", first, second))
                .orElse("-");
    }
}
