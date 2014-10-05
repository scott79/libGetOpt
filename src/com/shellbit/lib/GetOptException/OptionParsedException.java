package com.shellbit.lib.GetOptException;

public class OptionParsedException extends Exception{

	private String message = "OptionParsedException";

	public OptionParsedException(){
		message = "OptionParsedException: Error parsing arguments";
	}
	public OptionParsedException(String mesg){
		message = String.format("%s: %s", message,mesg);
	}
	
	public String toString(){
		return message;
	}
}