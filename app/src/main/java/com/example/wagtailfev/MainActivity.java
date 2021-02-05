package com.example.wagtailfev;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private final Florence flory = new Florence(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateLatest();
    }

    void updateLatest() {
        TextView text = (TextView)findViewById(R.id.textView);
        text.setText(flory.getLatest());
        clearEditText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_clear) {
            clearRecord();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void clearRecord() {
        flory.deleteFile();
        updateLatest();
    }

    void clearEditText() {
        getEditText().setText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        flory.appendRecord(readEditText());
    }

    private String readEditText() {
        return getEditText().getText().toString();
    }

    EditText getEditText() {
        return (EditText)findViewById(R.id.editText);
    }
}
