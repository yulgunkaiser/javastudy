package com.example.csv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvFileRead {
    public List<String> read(String fileName, String headerName)
     {
         List<String> keyWords=new ArrayList<>();

        Reader inputFile= null;
        try {
            inputFile = new FileReader("logs\\search-content.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Iterable<CSVRecord> records= null;
        try {
            records = CSVFormat.RFC4180.withHeader("Words").parse(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (CSVRecord csvRecord:records)
        {
            String words=csvRecord.get(headerName);
            keyWords.add(words);
            System.out.println(String.format("%s",words));
        }
        return keyWords;

    }
}
