package com.peakenza.dto;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ExcelDataToJavaListTest {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("c:/test.xlsx");
        List<EmployeeExcel> invoices = Poiji.fromExcel(new FileInputStream(file), PoijiExcelType.XLSX, EmployeeExcel.class);
        System.out.println("Printing List Data: " + invoices);
    }
}
