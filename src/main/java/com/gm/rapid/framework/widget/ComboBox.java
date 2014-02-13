package com.gm.rapid.framework.widget;

public class ComboBox {
	public ComboBox(String value,String text){
		this.value = value;
		this.text = text;
	}
	
	private String value;
	private String text;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}