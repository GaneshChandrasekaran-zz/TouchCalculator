package com.example.mycalculator;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class KeypadAdapter extends BaseAdapter {

	private Context context;

	private OnClickListener onButtonClick;

	public KeypadAdapter(Context c) {
		context = c;
	}

	public void setOnButtonClickListener(OnClickListener listener) {
		onButtonClick = listener;
	}

	private KeypadButton[] buttons = { KeypadButton.DUMMY, KeypadButton.DUMMY, KeypadButton.DUMMY, KeypadButton.C,
			KeypadButton.SEVEN, KeypadButton.EIGHT, KeypadButton.NINE, KeypadButton.DIV, 
			KeypadButton.FOUR, KeypadButton.FIVE, KeypadButton.SIX, KeypadButton.MULTIPLY, 
			KeypadButton.ONE, KeypadButton.TWO, KeypadButton.THREE, KeypadButton.MINUS,
			KeypadButton.DECIMAL_SEP, KeypadButton.ZERO, KeypadButton.CALCULATE, KeypadButton.PLUS };

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return buttons.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return buttons[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Button btn;

		if (arg1 == null) { 
			btn = new Button(context);
			KeypadButton keypadButton = buttons[arg0];

			if (keypadButton != KeypadButton.DUMMY)
				btn.setOnClickListener(onButtonClick);
			else
				btn.setClickable(false);

			btn.setTag(keypadButton);
		} else {
			btn = (Button) arg1;
		}

		btn.setText(buttons[arg0].getText());
		return btn;
	}
}
