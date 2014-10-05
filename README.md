libGetOpt
=========

Java Cli Get Option Lib Usage Example:


    import com.shellbit.lib.GetOpt.*;

    public class TestGetOpt{
        public static void main(String[] args){
        
            GetOpt options = new GetOpt();
        
            try{
        
        
                // Register option
                options.registerOption("casper");
            
                // Register option with specific option type
                options.registerOption("h","boolean");
                options.registerOption("test","boolean");
            
                // Register required option
                options.registerOption("today",true);
            
                // Set args to process cli arguments
                options.setOptionList(args);
            
                System.out.println("Value for test: " + options.getOption("test").getValue());
                System.out.println("Value for today: " + options.getOption("today").getValue());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


