package com.twist.twistautomation.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import io.netty.util.internal.StringUtil;
import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class CsvUtils {

    public static String updateCsvAsPerTubePosition(String fileToUpdate, String rackId, List<String> tubeIds) throws Exception {
        File inputFile = new File(fileToUpdate);
        // Read existing file
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileToUpdate)).withSkipLines(0).build();
        List<String[]> csvBody = reader.readAll();
        // get CSV row column and replace with by using row and column
        int numberOfTubes = tubeIds.size();
        for (int i = 0; i < csvBody.size(); i++) {
            String[] strArray = csvBody.get(i);
            int finalI = i;
            if (!strArray[0].equalsIgnoreCase("Tube Position")) {
                csvBody.get(finalI)[1] = "";
                csvBody.get(finalI)[2] = rackId;
                csvBody.get(finalI)[3] = DateHelper.getTodayDateYYYYMMDD();
                csvBody.get(finalI)[5] = "No Tube";
                if (numberOfTubes != 0) {
                    csvBody.get(finalI)[1] = tubeIds.get(i - 1);
                    csvBody.get(finalI)[5] = "OK";
                    numberOfTubes--;
                }
            }

            /* When required to update by tube position
            if (strArray[0].equalsIgnoreCase(tubePosition)) {
                    data.forEach((k, v) -> {
                        System.out.println(k + "---" + v);
                        csvBody.get(finalI)[k] = v;
                    });
            }*/
        }
        reader.close();
        // Write to CSV file which is open
        /*CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
        System.out.println(csvBody);
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();*/
        String csv = "";
        for (String[] rows :
                csvBody) {
            AtomicReference<String> row = new AtomicReference<>("");
            Arrays.stream(rows).forEach(s -> {
                row.set(row + s + StringUtil.COMMA);
            });
            String str = row.toString();
            str = str.substring(0, str.length() - 1);
            csv = csv + str + "\\r\\n";
        }

        return csv;
    }

    public static String updateCsvAsPerTubePosition(String fileToUpdate, String rackId, Map<String, String> tubeIdWithPosition) throws Exception {
        File inputFile = new File(fileToUpdate);
        // Read existing file
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileToUpdate)).withSkipLines(0).build();
        List<String[]> csvBody = reader.readAll();
        // get CSV row column and replace with by using row and column
        int numberOfTubes = tubeIdWithPosition.size();
        for (int i = 0; i < csvBody.size(); i++) {
            String[] strArray = csvBody.get(i);
            int finalI = i;
            if (!strArray[0].equalsIgnoreCase("Tube Position")) {
                csvBody.get(finalI)[1] = "";
                csvBody.get(finalI)[2] = rackId;
                csvBody.get(finalI)[3] = DateHelper.getTodayDateYYYYMMDD();
                csvBody.get(finalI)[5] = "No Tube";
                if (tubeIdWithPosition.containsKey(csvBody.get(finalI)[0])) {
                    csvBody.get(finalI)[1] = tubeIdWithPosition.get(csvBody.get(finalI)[0]);
                    csvBody.get(finalI)[5] = "OK";
                    numberOfTubes--;
                }
            }
        }
        reader.close();
        String csv = "";
        for (String[] rows :
                csvBody) {
            AtomicReference<String> row = new AtomicReference<>("");
            Arrays.stream(rows).forEach(s -> {
                row.set(row + s + StringUtil.COMMA);
            });
            String str = row.toString();
            str = str.substring(0, str.length() - 1);
            csv = csv + str + "\\r\\n";
        }
        return csv;
    }

    public static String updatePlateScanFileAndGetData(String rackId, List<String> tubeIds) {
        String csvContent = null;
        try {
            csvContent = updateCsvAsPerTubePosition("src/test/resources/data/fileToUpload/sample_plate_scan.csv",
                    rackId, tubeIds);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("csv file update failed with error");
        }
        return csvContent;
    }

}
