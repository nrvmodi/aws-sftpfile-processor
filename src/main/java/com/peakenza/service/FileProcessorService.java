package com.peakenza.service;

import com.amazonaws.services.transfer.model.S3FileLocation;
import com.peakenza.dto.EmployeeExcel;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.apache.commons.beanutils.PropertyUtils;
import software.amazon.awssdk.core.ResponseInputStream;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class FileProcessorService {

    public void processFile(Map event) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        S3FileLocation s3FileLocation = (S3FileLocation) PropertyUtils.getNestedProperty(event, "fileLocation");

        //TODO: Need to retrieve that file from s3 bucket and do any type of processing
        AwsS3Service awsS3Service = new AwsS3Service();

        ResponseInputStream inputStream = awsS3Service.readFile(s3FileLocation.getBucket(), s3FileLocation.getKey());
        List<EmployeeExcel> employeeExcels = Poiji.fromExcel(inputStream, PoijiExcelType.XLS, EmployeeExcel.class);
        //TODO: If you get file from s3 then you can pass that file in below method and validate it.
        validateFile(event);

    }

    private void validateFile(Map event) {

    }
}
