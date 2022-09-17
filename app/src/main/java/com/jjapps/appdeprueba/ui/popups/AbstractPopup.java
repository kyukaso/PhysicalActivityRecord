package com.jjapps.appdeprueba.ui.popups;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.jjapps.appdeprueba.R;

public abstract class AbstractPopup extends Dialog {

    public AbstractPopup(@NonNull Context context) {super(context);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(content_view());
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.getWindow().getAttributes().windowAnimations = R.style.PopupAnimation;
        this.getWindow().setGravity(Gravity.BOTTOM);
    }

    public abstract int content_view();
}
