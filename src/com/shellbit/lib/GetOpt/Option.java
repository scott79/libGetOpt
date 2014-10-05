package com.shellbit.lib.GetOpt;

public class Option{

	private String name;
	private String type = "String";
	private String value;
	private boolean required = false;

	protected Option(){
		name = null;
	}
	protected Option(String name, String value){
		this.name = name;
		this.value = value;
	}
	protected Option(String name, String type, String value){
		this.name = name;
		this.type = type;
		this.value = value;
	}
	protected void setName(String name){
		this.name = name;
	}
	protected String getName(){
		return name;
	}
	protected void setType(String type){
		this.type = type;	
	}
	public String getType(){
		return type;
	}
	protected void setValue(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
	public boolean isRequired(){
		return required;
	}
	protected boolean isRequired(boolean req){

		required = req;

		return required;
	}
}
