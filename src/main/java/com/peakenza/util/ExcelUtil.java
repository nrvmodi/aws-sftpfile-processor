package com.peakenza.util;

import com.peakenza.dto.Employee;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExcelUtil {

    public void readFile(InputStream inputStream) throws IOException {
        List<Employee> employees = Lists.newArrayList();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Employee employee = new Employee();

            XSSFRow row = worksheet.getRow(i);

            employee.setId((int) row.getCell(0).getNumericCellValue());
            employees.add(employee);
        }
    }

    public static void main(String[] args) {

    }
}
