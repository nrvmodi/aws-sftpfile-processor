package com.peakenza.dto;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelDataToJavaListTest {

    public static void main(String[] args) throws IOException {

        File file = new File("e:/test.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
//        byte[] bytes = IOUtils.toByteArray(fileInputStream);
        List<EmployeeExcel> invoices = Poiji.fromExcel(fileInputStream, PoijiExcelType.XLSX, EmployeeExcel.class);
        System.out.println("Printing List Data: " + invoices);
    }
}
