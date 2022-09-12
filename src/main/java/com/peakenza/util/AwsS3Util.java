package com.peakenza.util;

import software.amazon.awssdk.services.s3.S3Client;

public class AwsS3Util {

    private static S3Client s3Client;

    public static S3Client getS3Client() {
        if (s3Client == null) {
            s3Client = S3Client.builder().build();
        }
        return s3Client;
    }


}
