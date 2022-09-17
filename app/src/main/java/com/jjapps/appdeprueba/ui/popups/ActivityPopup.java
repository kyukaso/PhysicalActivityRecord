package com.jjapps.appdeprueba.ui.popups;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.jjapps.appdeprueba.R;
import com.jjapps.appdeprueba.databases.activityDatabase.DataManager;
import com.jjapps.appdeprueba.models.DailyActivity;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ActivityPopup extends AbstractPopup{
    private final DailyActivity activitySelected;
    private LocalDate date;
    private Spinner weightSpinner;
    private EditText calInput;
    private EditText kmInput;
    private EditText tmpInput;
    private Button activitySubmit;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ActivityPopup(@NonNull Context context, DailyActivity activity) {
        super(context);
        activitySelected = activity;
        date = LocalDate.ofEpochDay(activity.day);
    }

    @Override
    public int content_view() {
        return R.layout.activity_popup_dialog;
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title = this.findViewById(R.id.activityTitle);
        title.setText(String.format(Locale.getDefault(),
                this.getContext().getString(R.string.popup_date_format), date.getDayOfMonth(), date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())));

        activitySubmit = this.findViewById(R.id.activitySubmit);
        calInput = this.findViewById(R.id.calInput);
        kmInput = this.findViewById(R.id.kmInput);
        tmpInput = this.findViewById(R.id.timeInput);

        if (activitySelected.activity >= 1){
            calInput.setText(String.valueOf(activitySelected.calories) + " Cal");
            kmInput.setText(String.valueOf(activitySelected.kilometres) + " Kg");
            tmpInput.setText(String.valueOf(activitySelected.time) + " H");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(){
        if (calInput.getText().toString().equals("") || kmInput.getText().toString().equals("")
                || tmpInput.getText().toString().equals("")){
            Toast.makeText(this.getContext(), this.getContext().getString(R.string.activity_toast_text), Toast.LENGTH_SHORT).show();
        }
        else {
            activitySelected.activity = 1;
            activitySelected.calories = Integer.parseInt(calInput.getText().toString().replace("Cal", "").trim());
            activitySelected.kilometres = Float.parseFloat(kmInput.getText().toString().replace("Kg", "").trim());
            activitySelected.time = Float.parseFloat(tmpInput.getText().toString().replace(" H", "").trim());
            DataManager.get(this.getContext()).insertActivity(activitySelected);
            this.dismiss();
        }
    }



    public Button getActivitySubmit() {
        return activitySubmit;
    }
}
