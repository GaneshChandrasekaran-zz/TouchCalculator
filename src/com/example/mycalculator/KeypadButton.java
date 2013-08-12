package com.example.mycalculator;

public enum KeypadButton {

	C("C",KeypadButtonCategory.CLEAR)
	, ZERO("0",KeypadButtonCategory.NUMBER)
	, ONE("1",KeypadButtonCategory.NUMBER)
	, TWO("2",KeypadButtonCategory.NUMBER)
	, THREE("3",KeypadButtonCategory.NUMBER)
	, FOUR("4",KeypadButtonCategory.NUMBER)
	, FIVE("5",KeypadButtonCategory.NUMBER)
	, SIX("6",KeypadButtonCategory.NUMBER)
	, SEVEN("7",KeypadButtonCategory.NUMBER)
	, EIGHT("8",KeypadButtonCategory.NUMBER)
	, NINE("9",KeypadButtonCategory.NUMBER)
	, DECIMAL_SEP(".",KeypadButtonCategory.OTHER)
	, PLUS(" + ",KeypadButtonCategory.OPERATOR)
	, MINUS(" - ",KeypadButtonCategory.OPERATOR)
	, MULTIPLY(" * ",KeypadButtonCategory.OPERATOR)
	, DIV(" / ",KeypadButtonCategory.OPERATOR)
	, CALCULATE("=",KeypadButtonCategory.RESULT)
	, DUMMY("",KeypadButtonCategory.DUMMY);

	CharSequence text;
	KeypadButtonCategory category;
	
	KeypadButton(CharSequence text,KeypadButtonCategory category) {
		this.text = text;
		this.category = category;
	}

	public CharSequence getText() {
		return this.text;
	}


}
