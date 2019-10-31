package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * 对比结果
 */
@Data
public class CompareResult {

    private String database;
    List<CompScore> scoreList;

}
