package edu.eskisehir.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.eskisehir.model.Record;

    public class DatasetIO {
        public static List<Record> readDataset(String filename) throws IOException {
            try (InputStream stream = new FileInputStream(filename)) {
                Scanner scanner = new Scanner(stream);
                String headerLine = scanner.nextLine();
                String[] headers = headerLine.split(",");
                List<Record> records = new ArrayList<>();
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] cells = line.split(",");
                    String year = cells[0];
                    for (int i = 1; i < cells.length; i++) {
                        Record record = new Record();
                        record.setMonth(headers[i]);
                        record.setValue(Integer.parseInt(cells[i]));
                        record.setYear(year);
                        records.add(record);
                    }
                }
                return records;
            }
        }
    }

