package com.example.thaianhit.citytravel;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = 1995;
        int month = 06;
        int day = 13;
        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}