/*
* By: scott@shellbit.com
* Date: 10/03/2014
*
*/

package com.shellbit.lib.GetOpt;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;
import com.shellbit.lib.GetOptException.*;
import com.shellbit.lib.GetOpt.Option;

public class GetOpt{

	private String[] optionList;
	private HashMap<String,Option> options;
	private HashMap<String,Option> registeredOptions;
	private static boolean OPTIONS_PARSED = false;
        private static boolean DEBUG = false;

	// Main constructors
	public GetOpt(){
		optionList = null;
		options = new HashMap<String,Option>();
		registeredOptions = new HashMap<String,Option>();
	}

	public GetOpt(String[] args){

		optionList = args;
		options = new HashMap<String,Option>();
		registeredOptions = new HashMap<String,Option>();
	}

	// Misc methods
	public void setOptionList(String[] args) throws OptionParsedException, OptionSetException{

		if(!isOptionsParsed()){
			optionList = args;
			parseOptions();
		}else{
			throw new OptionSetException("Option list already parsed. See reset.");
		}
	}
	/**
		Method to type convert requeted options
	*/
	public void registerOption(String name) throws OptionSetException, OptionTypeException{
		registerOption(name,"String");
	}
	public void registerOption(String name, boolean required) throws OptionSetException, OptionTypeException{
		registerOption(name,"String",required);
	}
	public void registerOption(String name, String type) throws OptionSetException, OptionTypeException{
		registerOption(name,type,false);
	}
	public void registerOption(String name, String type, boolean required) throws OptionSetException, OptionTypeException{

		if(!isOptionsParsed()){
                    
			if(name != null && name.length() > 0){
				if(!registeredOptions.containsKey(name)){

					Option opt = new Option();
					opt.setName(name);
					opt.setType(type);
					opt.isRequired(required);
					registeredOptions.put(name,opt);
				}	
			}
		}else{
			throw new OptionSetException();
		}
	}

	/**
		getOption method 
	*/
        public Option getOption(String name) throws OptionParsedException, OptionSetException{
            
            if(!isOptionsParsed())
                parseOptions();
            
            if(!options.containsKey(name))
                throw new OptionSetException("Option not set: " + name);
            
            return options.get(name);
            
        }
	public String getOptionValue(String name) throws OptionParsedException{

		String value = null;

		if(!isOptionsParsed())
			parseOptions();
		

		if(options.containsKey(name)){
			value = options.get(name).getValue();		
		}

		return value;
	}

	public boolean reset(boolean rst){

		boolean CURRENT_OPTIONS_PARSED = OPTIONS_PARSED;

		if(rst){

			// Clear out list
			options = null;
			optionList = null;

			OPTIONS_PARSED = false;
			CURRENT_OPTIONS_PARSED = OPTIONS_PARSED;
		}

		return CURRENT_OPTIONS_PARSED;
	}	
	/**
	  Parse methods

	  Support the following option definitions:

	  --option value
	   -option value
	    option value
	*/
	public void parseOptions() throws OptionParsedException{

		if(isOptionsParsed())
			throw new OptionParsedException();

		if(optionList.length > 0 && registeredOptions.size() > 0){
                    
                        int totalRequiredOptions = 0;
                        int totalRequiredOptionsSet = 0;
                        
                        Set<String> reqOptions = registeredOptions.keySet();
                        
                        // Verify required options set
                        for(String ropt: reqOptions){
                            if(registeredOptions.get(ropt).isRequired())
                                totalRequiredOptions++;
                        }
                        
			for(int i = 0 ; i < optionList.length ; i++){
                                
                            String name = optionList[i].trim();
                            
                            name = name.replaceAll("^-+","");
                                
                            if(DEBUG)
                                System.out.println("Checking arg:" + name);

                            if(registeredOptions.containsKey(name) && !options.containsKey(name)){

                                Option opt = registeredOptions.get(name);
                                String type = opt.getType().toLowerCase();

                                if(opt.isRequired()){
                                    totalRequiredOptionsSet++;
                                }
                                switch(type){
                                    case "string":
                                            opt.setValue(optionList[i+1]);
                                            i++; 
                                            break;
                                    case "int":
                                            opt.setValue(optionList[i+1]);
                                            i++; 
                                            break;
                                    case "boolean": 
                                            opt.setValue("true");
                                            break;
                                }
                                options.put(name,opt);	
                            }
			}
                        if(totalRequiredOptions != totalRequiredOptionsSet){
                            String optionsMissing = "";
                            for(String opt: registeredOptions.keySet()){
                                if(!options.containsKey(opt) && registeredOptions.get(opt).isRequired()){
                                    optionsMissing += String.format(opt + " ");
                                }
                            }
                            throw new OptionParsedException("Required option(s) missing: " + optionsMissing);
                        }
                        OPTIONS_PARSED = true;
		}	

	}
	public void parseOptions(String[] args) throws OptionParsedException{
		optionList = args;

		parseOptions();
	}
        public boolean isOptionsParsed(){
            return OPTIONS_PARSED;
        }
        public void debug(boolean status){
            DEBUG = status;
        }
}
