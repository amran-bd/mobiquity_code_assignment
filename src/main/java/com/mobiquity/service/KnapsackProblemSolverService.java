package com.mobiquity.service;

import com.mobiquity.dto.KnapsackProblemDTO;
import com.mobiquity.dto.ResultDTO;

/**
 * @author AmranHossain on 19/9/2564
 */
public interface KnapsackProblemSolverService {

    ResultDTO solution(KnapsackProblemDTO problem);
}
