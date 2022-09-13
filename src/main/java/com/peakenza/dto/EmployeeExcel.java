package com.peakenza.dto;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class EmployeeExcel {
    @ExcelRow
    private int rowIndex;

    @ExcelCellName("Employee Name")
    private String name;

    @ExcelCellName("Org Level Code")
    private Integer amount;

    @Override
    public String toString() {
        return "EmployeeExcel{" +
                "rowIndex=" + rowIndex +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
