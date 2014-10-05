package com.shellbit.lib.GetOptException;

public class OptionSetException extends Exception{

	private String message = "OptionSetException";

	public OptionSetException(){
		message = "Option Not Found";
	}

	public OptionSetException(String mesg){
		message = String.format("%s: %s", message,mesg);
	}

	public String toString(){
		return message;
	}
}
