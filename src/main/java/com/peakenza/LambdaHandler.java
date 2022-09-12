package com.peakenza;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.transfer.model.SendWorkflowStepStateRequest;
import com.amazonaws.services.transfer.model.SendWorkflowStepStateResult;
import com.peakenza.service.FileProcessorService;
import com.peakenza.util.AwsTransferUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.lambda.powertools.logging.Logging;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LambdaHandler implements RequestHandler<Map, Object> {

    Logger log = LogManager.getLogger();

    @Override
    @Logging
    public Object handleRequest(Map event, Context context) {

        Map response = new HashMap();

//        LoggingUtils.appendKey("event", "willBeLogged");
        log.info("Aws sftp file processor event: {}", event);
        SendWorkflowStepStateRequest request = new SendWorkflowStepStateRequest();
        try {
            String executionId = BeanUtils.getNestedProperty(event, "serviceMetadata.executionDetails.executionId");
            log.info("Aws sftp file processor fetched executionId: {} for event: {}", executionId, event);
            String workflowId = BeanUtils.getNestedProperty(event, "serviceMetadata.executionDetails.workflowId");
            log.info("Aws sftp file processor fetched workflowId: {} for event: {}", workflowId, event);
            String token = BeanUtils.getProperty(event, "token");
            log.info("Aws sftp file processor token retrieved for event: {}", event);
            request.setExecutionId(executionId);
            request.setWorkflowId(workflowId);
            request.setToken(token);

            //TODO : Do any file processing, like file validation or data retrieve and call any api to submit that file data
            doFileProcess(event);

            log.info("Aws sftp file processor completed for event: {}", event);

            request.setStatus("SUCCESS");
        } catch (Exception e) {
            request.setStatus("FAILURE");
            log.error("Error while processing : event: {}, error: {}", event, e.getMessage(), e);
        }

        try {

            log.info("Aws sftp file processor sendWorkflowStepState : event: {}, request: {}", event, request);

            SendWorkflowStepStateResult result = AwsTransferUtil.getTransfer().sendWorkflowStepState(request);

            response.put("statusCode", 200);
            response.put("body", result);
            log.info("Aws sftp file processor sendWorkflowStepState completed : event: {}, request: {}, response: {}", event, request, result);
        } catch (Exception e) {
            response.put("statusCode", 500);
            response.put("body", e);
            log.error("Error while sendWorkflowStepState : event: {}, request: {}, error: {}", event, request, e.getMessage(), e);
        }

        return response;
    }

    private void doFileProcess(Map event) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        FileProcessorService fileProcessorService = new FileProcessorService();
        fileProcessorService.processFile(event);
    }

}
