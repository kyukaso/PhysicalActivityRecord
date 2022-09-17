package com.jjapps.appdeprueba.ui.popups;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.jjapps.appdeprueba.databases.weightDatabase.WeightManager;
import com.jjapps.appdeprueba.models.WeightData;
import com.jjapps.appdeprueba.R;

import java.time.LocalDate;
import java.util.Locale;

public class WeightPopup extends AbstractPopup {
    private EditText weightInput;
    private Button submit;

    public WeightPopup(@NonNull Context context) {super(context);}

    @Override
    public int content_view() {
        return R.layout.weight_popup_dialog;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title = this.findViewById(R.id.dayWeight);
        title.setText(String.format(Locale.getDefault(),
                this.getContext().getString(R.string.popup_date_format), LocalDate.now().getDayOfMonth(), LocalDate.now().getMonth().toString()));
        this.weightInput = this.findViewById(R.id.editWeight);
        this.submit = this.findViewById(R.id.submitWeight);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick() {
        if (weightInput.getText().toString().equals("")) {
            Toast.makeText(this.getContext(), this.getContext().getString(R.string.weight_toast_text), Toast.LENGTH_SHORT).show();
            return;
        }

        WeightData weight = new WeightData(LocalDate.now().toEpochDay(),
                Float.parseFloat(weightInput.getText().toString()));
        WeightManager.get(this.getContext()).insertWeight(weight);
        this.dismiss();
    }

    public Button getSubmit() {
        return submit;
    }
}
