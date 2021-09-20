package com.mobiquity.packer;

import com.mobiquity.dto.ResultDTO;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.KnapsackProblemService;
import com.mobiquity.service.KnapsackProblemServiceImpl;
import com.mobiquity.service.KnapsackProblemSolverService;
import com.mobiquity.service.KnapsackProblemSolverServiceImpl;

public class Packer {

  private Packer() {
  }

  public static String pack(String filePath) throws APIException {
    final KnapsackProblemService reader = new KnapsackProblemServiceImpl();
    final KnapsackProblemSolverService solver = new KnapsackProblemSolverServiceImpl();
    try {
      return reader.read(filePath)
              .map(solver::solution)
              .map(ResultDTO::toString)
              .reduce((first, second) -> String.format("%s\r\n%s", first, second))
              .orElse("-");
    } catch (IllegalArgumentException e) {
      throw new APIException("Gotten Error while read file!", e);
    }
  }
}
