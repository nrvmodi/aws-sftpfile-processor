package com.peakenza.dto;

import com.poiji.bind.Poiji;

import java.io.File;
import java.util.List;

public class ExcelDataToJavaListTest {

    public static void main(String[] args) {

        File file = new File("c:/test.xlsx");
        List<EmployeeExcel> invoices = Poiji.fromExcel(file, EmployeeExcel.class);
        System.out.println("Printing List Data: " + invoices);
    }
}
