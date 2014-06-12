package com.fanikiosoftware.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TipCalculator extends Activity {
	EditText etBill;	
	EditText etTip;
	Double bill;
	Double percent;
	public TextView tvTip;
	public TextView tvTotal;
	Double total;
	RadioGroup mRadioGroup;
	RadioButton rd_low;
	RadioButton rd_mid;
	RadioButton rd_high;
	private SeekBar seekBar;
	private TextView tvSBValue;
	Double tip;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		etBill = (EditText)findViewById(R.id.etBill);
		tvTip = (TextView)findViewById(R.id.tvTip);
		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		rd_low = (RadioButton)findViewById(R.id.rd_low);
		rd_mid = (RadioButton)findViewById(R.id.rd_mid);
		rd_high = (RadioButton)findViewById(R.id.rd_high);
		tvSBValue = (TextView) findViewById(R.id.tvSBValue);
		tvTotal =(TextView) findViewById(R.id.tvTotal);
		seekBar = (SeekBar) findViewById(R.id.seekBar);
	
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser){						
				if(etBill.getText().length() == 0 ){
					etBill.setError("Please make a valid entry.");
					resetAll();
					return;
				}else {					
					tvSBValue.setText(progress +"%");
					if(progress>0){
						mRadioGroup.clearCheck();
					}else{		
						Double percent = ((double) progress); 
						Double bill = Double.parseDouble(etBill.getText().toString());			
						Double tip = (bill*percent)/100;
						Double total = bill + tip;
						tvTip.setText("Tip: $"+ String.format("%.02f", tip));
						tvTotal.setText("Grand Total: $"+ String.format("%.02f", total));
					}
				}				
				return;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void resetAll(View v){
		resetAll();	
	}
	
	public void resetAll(){
		etBill.requestFocus();
		mRadioGroup.clearCheck();
		seekBar.setProgress(0);		
		etBill.setText("");
		tvTip.setText("");
		tvTotal.setText("");
		tvSBValue.setText("");		
		return;
	}

	public void setTipPercentage(View view){
		if(etBill.getText().length() == 0 ){
			etBill.setError("Please make a valid entry.");
			resetAll();
			return;
		}else {	
			seekBar.setProgress(0);	
			boolean checked = ((RadioButton)view).isChecked();			
			tvSBValue.setText("");
			switch(view.getId()){
				case R.id.rd_low:
					if(checked){						
						percent = .15;			
						break;
					}
				case R.id.rd_mid:
					if(checked){
						percent = .20;
						break;
					}
				case R.id.rd_high:
					if(checked){
						percent = .25;
						break;
					}					
			}
			Double bill = Double.parseDouble(etBill.getText().toString());
			Double total = bill + (bill * percent);
			Double tip = bill*percent;
			tvTip.setText("Tip: $"+ String.format("%.02f", tip));
			tvTotal.setText("Grand Total: $"+ String.format("%.02f", total));
			seekBar.setProgress(0);
			return;
		}
	}
}