package com.ghrtec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BayesCalculatorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hypothesis);
        startActivity(new Intent(this,GridCalcActivity.class));
        finish();
    }
}