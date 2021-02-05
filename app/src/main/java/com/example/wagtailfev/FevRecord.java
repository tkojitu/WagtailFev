package com.example.wagtailfev;

import androidx.annotation.NonNull;

class FevRecord {
    private final StringBuilder buffer = new StringBuilder();
    private String latest;

    void append(String str) {
        if (str == null)
            return;
        String trimed = str.trim();
        if (trimed.isEmpty())
            return;
        buffer.append(trimed).append('\n');
        latest = trimed;
    }

    @NonNull
    public String toString() {
        return buffer.toString();
    }

    String getLatest() {
        return latest;
    }
}
