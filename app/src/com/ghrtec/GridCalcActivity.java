package com.ghrtec;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class GridCalcActivity extends Activity {

	private double ph1,ph2,peh1,peh2,phe1,phe2;
	private boolean hasAll;

	private boolean calcing=false;
	
	private double loadDoubleFromTextBox(int id)
	{
		try
		{
			return Double.parseDouble(((TextView)findViewById(id)).getText().toString());
		}
		catch ( NumberFormatException e )
		{
			hasAll = false;
		}
		return 0;
	}

	private void putDoubleInBox(int id, double val)
	{
		putDoubleInBox(id, val,"");
	}
	
	private void putDoubleInBox(int id, double val, String post)
	{
		((TextView)findViewById(id)).setText(String.format("%.2f", val)+post);
	}

	private void loadValues()
	{
		hasAll = true;

		ph1 = loadDoubleFromTextBox(R.id.ph1)/100.;
		ph2 = loadDoubleFromTextBox(R.id.ph2)/100.;
		peh1 = loadDoubleFromTextBox(R.id.peh1)/100.;
		peh2 = loadDoubleFromTextBox(R.id.peh2)/100.;
	}

	private void doBayes()
	{
		phe1 = (peh1*ph1)/(peh1*ph1+peh2*(1-ph1));
		phe2 = 1-phe1;
	}

	private void showValues()
	{
		putDoubleInBox(R.id.phe1, phe1*100.,"%");
		putDoubleInBox(R.id.phe2, phe2*100.,"%");
	}
	
	private void putBlanks()
	{
		((TextView)findViewById(R.id.phe1)).setText("-");
		((TextView)findViewById(R.id.phe2)).setText("-");
	}

	private void doCalculate()
	{
		if ( hasAll && ph1+ph2 == 1)
		{
			doBayes();
			showValues();
		}
		else
		{
			putBlanks();
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridcalc);

		((EditText)findViewById(R.id.ph1)).addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			public void afterTextChanged(Editable s) {

				loadValues();
				
				if (!calcing)
				{
					calcing = true;
					putDoubleInBox(R.id.ph2,(1-ph1)*100);
					calcing = false;
				}
				
				doCalculate();
			}
		});

		((EditText)findViewById(R.id.ph2)).addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			public void afterTextChanged(Editable s) {

				loadValues();
				
				if (!calcing)
				{
					calcing = true;
					putDoubleInBox(R.id.ph1,(1-ph2)*100);
					calcing = false;
				}
					
				
				doCalculate();
			}
		});

		((EditText)findViewById(R.id.peh1)).addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			public void afterTextChanged(Editable s) {

				loadValues();
				doCalculate();
			}
		});

		((EditText)findViewById(R.id.peh2)).addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			public void afterTextChanged(Editable s) {
				
				loadValues();
				doCalculate();
			}
		});
	}

}
