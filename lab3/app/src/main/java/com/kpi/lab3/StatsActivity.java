package com.kpi.lab3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.Fragment;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsActivity extends AppCompatActivity {
    private Map<String, String> stats;
    private Activity prefActivity;

    public StatsActivity() {
    }

    public void setPrefActivity(Activity prefActivity) {
        this.prefActivity = prefActivity;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences(String.valueOf(R.string.stats), Context.MODE_PRIVATE);
        stats = (Map<String, String>) sharedPreferences.getAll();
        setContentView(R.layout.activity_stats);
        TableLayout tbl = findViewById(R.id.stats_table);
        for (Map.Entry<String, String> entry:stats.entrySet()) {
            addTableRow(tbl, entry);
        }
        if (stats.isEmpty()) {
            addInfoString(tbl);
        }
    }

    private void addInfoString(TableLayout tbl) {
        Context context = this;
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView emptyMap = new TextView(context);
        emptyMap.append("Nothing to be shown");
        tableRow.addView(emptyMap);
        tbl.addView(tableRow);
    }

    private void addTableRow(TableLayout tbl, Map.Entry<String, String> entry) {
        Context context = this;
        entry.getValue();
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView username = new TextView(context);
        username.setPadding(10, 3, 10, 3);
        username.append(
                entry.getKey());
        TextView result = new TextView(context);
        result.setPadding(10, 3, 10, 3);
        result.append(
                entry.getValue());
        tableRow.addView(username);
        tableRow.addView(result);
        tbl.addView(tableRow);
    }

    public void setStats(Map<String,String> stats) {
        this.stats = stats;
    }
}
