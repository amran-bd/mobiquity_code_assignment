package com.mobiquity.service;

import com.mobiquity.dto.KnapsackProblemDTO;
import com.mobiquity.exception.APIException;

import java.util.stream.Stream;

/**
 * @author AmranHossain on 19/9/2564
 */
public interface KnapsackProblemService {

    Stream<KnapsackProblemDTO> read(String uri) throws APIException;
}
