package com.peakenza.service;

import com.peakenza.util.AwsS3Util;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.cloudwatchlogs.emf.util.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AwsS3Service {

    private static final S3Client s3Client = AwsS3Util.getS3Client();

    public ResponseInputStream readFile(String bucketName, String fileName) throws IOException {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        ResponseInputStream responseInputStream = s3Client.getObject(getObjectRequest);
        byte[] bytes = IOUtils.toByteArray(responseInputStream);
        System.out.println("Content :" + new String(bytes, StandardCharsets.UTF_8));
        return responseInputStream;
    }
}
