package com.peakenza.service;

import com.amazonaws.services.transfer.model.S3FileLocation;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FileProcessorService {

    public void processFile(Map event) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        S3FileLocation s3FileLocation = (S3FileLocation) PropertyUtils.getNestedProperty(event, "fileLocation");

        //TODO: Need to retrieve that file from s3 bucket and do any type of processing
        AwsS3Service awsS3Service = new AwsS3Service();

        //TODO: If you get file from s3 then you can pass that file in below method and validate it.
        validateFile(event);

    }

    private void validateFile(Map event) {

    }
}
