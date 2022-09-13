package com.peakenza.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class CsvToDtoUtil {

    private static Logger log = LogManager.getLogger(CsvToDtoUtil.class);

    public static <T> List<T> parse(Class<T> type, InputStream inputStream) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(inputStream);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error in parsing csv file: ", e);
            return Collections.emptyList();
        }
    }

    public static <T> List<T> parseWithException(Class<T> type, InputStream inputStream) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<T> readValues =
                mapper.reader(type).with(bootstrapSchema).readValues(inputStream);
        return readValues.readAll();
    }
}