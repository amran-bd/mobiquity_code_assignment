package com.mobiquity.service;

import com.mobiquity.dto.KnapsackItemDTO;
import com.mobiquity.dto.KnapsackProblemDTO;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FormatException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static com.mobiquity.util.KnapsackRegexUtils.ITEM_PATTERN;
import static com.mobiquity.util.KnapsackRegexUtils.LINE_PATTERN;

/**
 * @author AmranHossain on 19/9/2564
 */
public class KnapsackProblemServiceImpl implements KnapsackProblemService{
    @Override
    public Stream<KnapsackProblemDTO> read(String path) throws APIException {
        try {
            return Files.lines(Paths.get(path))
                    .filter(StringUtils::isNotBlank)
                    .map(this::parseLine);
        } catch (IOException e) {
            throw new APIException("Error occurred in reading the file", e);
        }
    }

    private KnapsackProblemDTO parseLine(String line) {
        validateLine(line);
        final String[] lineSections = line.split(":");
        return KnapsackProblemDTO.builder()
                .weightLimit(new BigDecimal(lineSections[0].trim()))
                .items(parseItems(lineSections[1]))
                .build();
    }

    private void validateLine(String line) {
        final Matcher matcher = LINE_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new FormatException(String.format("Unknown line format: '%s'", line));
        }
    }

    private List<KnapsackItemDTO> parseItems(String itemsSection) {
        final List<KnapsackItemDTO> items = new ArrayList<>();
        final Matcher matcher = ITEM_PATTERN.matcher(itemsSection);
        while (matcher.find()) {
            items.add(parseItem(matcher.group(1)));
        }
        return items;
    }

    private KnapsackItemDTO parseItem(String itemSection) {
        final String[] itemSections = itemSection.split(",");
        return KnapsackItemDTO.builder()
                .index(Integer.valueOf(itemSections[0]))
                .weight(new BigDecimal(itemSections[1].trim()))
                .cost(Integer.valueOf(itemSections[2].substring(1)))
                .build();
    }
}
