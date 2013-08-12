package com.example.mycalculator;

import java.util.Stack;
import com.example.mycalculator.KeypadButton;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
	GridView keypadGrid;
	KeypadAdapter keypadAdapter;
	TextView userInputText;

	Stack<String> operationStack;

	boolean resetInput = false;
	boolean hasFinalResult = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get reference to the keypad button GridView
		keypadGrid = (GridView) findViewById(R.id.grdButtons);

		// Create Keypad Adapter
		keypadAdapter = new KeypadAdapter(this);

		// Set adapter of the keypad grid
		keypadGrid.setAdapter(keypadAdapter);

		operationStack = new Stack<String>();

		// Get reference to the user input TextView
		userInputText = (TextView) findViewById(R.id.txtInput);
		userInputText.setText("0");

		// Set button click listener of the keypad adapter
		keypadAdapter.setOnButtonClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button btn = (Button) v;
				
				KeypadButton keypadButton = (KeypadButton) btn.getTag();
				
				processKeypadInput(keypadButton);
			}
		});
	}

	private void processKeypadInput(KeypadButton keypadButton) {

		String text = keypadButton.getText().toString();
		String currentInput = userInputText.getText().toString();
		
		String evalResult = null;

		switch (keypadButton) {

		case C:
			userInputText.setText("0");
			operationStack.clear();
			break;
		case DECIMAL_SEP:
			if (hasFinalResult || resetInput) {
				userInputText.setText("0" + ".");
				hasFinalResult = false;
				resetInput = false;
			} else if (currentInput.contains("."))
				return;
			else
				userInputText.append(".");
			break;
		case PLUS:
			if (resetInput) {
				operationStack.pop();
			} else {
				operationStack.add(currentInput);
			}

			operationStack.add(text);

			evalResult = evaluateResult(false);
			if (evalResult != null)
				userInputText.setText(evalResult);

			resetInput = true;
			break;

		case MINUS:
			if (resetInput) {
				operationStack.pop();
			} else {
				operationStack.add(currentInput);
			}

			operationStack.add(text);

			evalResult = evaluateResult(false);
			if (evalResult != null)
				userInputText.setText(evalResult);

			resetInput = true;
			break;

		case MULTIPLY:
			if (resetInput) {
				operationStack.pop();
			} else {
				operationStack.add(currentInput);
			}

			operationStack.add(text);

			evalResult = evaluateResult(false);
			if (evalResult != null)
				userInputText.setText(evalResult);

			resetInput = true;
			break;

		case DIV:
			if (resetInput) {
				operationStack.pop();
			} else {
				operationStack.add(currentInput);
			}

			operationStack.add(text);

			evalResult = evaluateResult(false);
			if (evalResult != null)
				userInputText.setText(evalResult);

			resetInput = true;
			break;

		case CALCULATE:
			if (operationStack.size() == 0)
				break;

			operationStack.add(currentInput);
			evalResult = evaluateResult(true);
			if (evalResult != null) {
				userInputText.setText(evalResult);
				resetInput = false;
				hasFinalResult = true;
			}
			break;

		default:
			if (Character.isDigit(text.charAt(0))) {
				if (currentInput.equals("0") || resetInput || hasFinalResult) {
					userInputText.setText(text);
					resetInput = false;
					hasFinalResult = false;
				} else {
					userInputText.append(text);
					resetInput = false;
				}
			}
			break;
		}
	}

	private String evaluateResult(boolean boolVal) {
		if ((!boolVal && operationStack.size() != 4)
				|| (boolVal && operationStack.size() != 3))
			return null;

		String left = operationStack.get(0);
		String operator = operationStack.get(1);
		String right = operationStack.get(2);
		String tmp = null;
		if (!boolVal)
			tmp = operationStack.get(3);

		double leftVal = Double.parseDouble(left.toString());
		double rightVal = Double.parseDouble(right.toString());
		double result = Double.NaN;

		if (operator.equals(KeypadButton.DIV.getText())) {
			result = leftVal / rightVal;
		} else if (operator.equals(KeypadButton.MULTIPLY.getText())) {
			result = leftVal * rightVal;

		} else if (operator.equals(KeypadButton.PLUS.getText())) {
			result = leftVal + rightVal;
		} else if (operator.equals(KeypadButton.MINUS.getText())) {
			result = leftVal - rightVal;
		}

		String resultStr = doubleToString(result);
		if (resultStr == null)
			return null;

		operationStack.clear();
		if (!boolVal) {
			operationStack.add(resultStr);
			operationStack.add(tmp);
		}
		return resultStr;
	}

	private String doubleToString(double value) {
		if (Double.isNaN(value))
			return null;

		long longVal = (long) value;
		if (longVal == value)
			return Long.toString(longVal);
		else
			return Double.toString(value);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
