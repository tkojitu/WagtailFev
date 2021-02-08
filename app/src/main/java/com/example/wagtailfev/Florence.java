package com.example.wagtailfev;

import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;

class Florence {
    private static final String FILENAME = "wagtailfev.txt";
    private final MainActivity activity;

    Florence(MainActivity arg) {
        activity = arg;
    }

    @NonNull
    FevRecord readRecord() {
        FevRecord rec = new FevRecord();
        try {
            FileInputStream fis = activity.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line = reader.readLine();
                while (line != null) {
                    rec.append(line);
                    line = reader.readLine();
                }
                return rec;
            } catch (IOException e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                return rec;
            }
        } catch (FileNotFoundException e) {
            return rec;
        }
    }

    void writeRecord(FevRecord rec) {
        try {
            FileOutputStream fos = activity.openFileOutput(FILENAME, MainActivity.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            try (BufferedWriter writer = new BufferedWriter(osw)) {
                writer.write(rec.toString());
            } catch (IOException e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void deleteFile() {
        new File(activity.getFilesDir(), FILENAME).delete();
    }

    @NonNull
    String getLatest() {
        return readRecord().getLatest();
    }

    void appendRecord(String str) {
        String s = str.trim();
        if (s.isEmpty()) {
            return;
        }
        FevRecord rec = readRecord();
        String timestamp = getTimestamp();
        rec.append(timestamp + "," + s);
        writeRecord(rec);
    }

    @NonNull
    String getTimestamp() {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
    }

    @NonNull
    String getRecords() {
        return readRecord().toString();
    }
}
