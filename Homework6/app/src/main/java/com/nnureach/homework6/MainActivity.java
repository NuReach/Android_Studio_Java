package com.nnureach.homework6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Calendar selectedDateTime = Calendar.getInstance();
    private  Calendar currentDateTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDate = findViewById(R.id.btnDate);
        Button btnClock = findViewById(R.id.btnClock);
        TextView tvResult = findViewById(R.id.tvResutl);
        Button btnExit = findViewById(R.id.btnExit);
        tvResult.setText("");

        btnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                (view, year1, month1, dayOfMonth) -> {
                    selectedDateTime.set(Calendar.YEAR, year1);
                    selectedDateTime.set(Calendar.MONTH, month1);
                    selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    Log.d("Date",selectedDateTime.toString());
                },
                currentDateTime.get(Calendar.YEAR),
                currentDateTime.get(Calendar.MONTH),
                currentDateTime.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });


        btnClock.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    MainActivity.this,
                    (view, hourOfDay, minute1) -> {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute1);

                        // Calculate the remaining time
                        long remainingTimeInMillis = selectedDateTime.getTimeInMillis() - System.currentTimeMillis();

                        // Convert the remaining time to days, months, years, hours, and minutes
                        long remainingDays = remainingTimeInMillis / (24 * 60 * 60 * 1000 * (-1));
                        long remainingMonths = remainingDays / 30; // Approximate months
                        long remainingYears = remainingDays / 365; // Approximate years
                        long remainingHours = (remainingTimeInMillis % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000*(-1));
                        long remainingMinutes = (remainingTimeInMillis % (60 * 60 * 1000)) / (60 * 1000*(-1));

                        // Log or use the remaining time as needed
                        String result = "Days: " + remainingDays +
                                " Months: " + remainingMonths +
                                " Years: " + remainingYears +
                                " Hours: " + remainingHours +
                                " Minutes: " + remainingMinutes;

                        tvResult.setText(result);
                    },
                    hour, minute, true);

            // Show the TimePickerDialog
            timePickerDialog.show();
        });

        btnExit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit Application");
            builder.setMessage("Are you sure you want to exit?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                // Exit the application
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                // Dismiss the dialog if the user clicks "No"
                dialog.dismiss();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}