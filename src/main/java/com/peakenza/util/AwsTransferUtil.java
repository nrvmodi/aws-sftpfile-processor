package com.peakenza.util;

import com.amazonaws.services.transfer.AWSTransfer;
import com.amazonaws.services.transfer.AWSTransferClient;

public class AwsTransferUtil {

    private static AWSTransfer transfer;

    public static AWSTransfer getTransfer() {
        if (transfer == null) {
            transfer = AWSTransferClient.builder().build();
        }
        return transfer;
    }


}
