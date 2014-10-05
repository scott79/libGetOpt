import com.shellbit.lib.GetOpt.*;

public class TestGetOpt{
    public static void main(String[] args){
        
        GetOpt options = new GetOpt();
        
        try{
            
            options.registerOption("h","boolean");
            options.registerOption("test","boolean");
            options.registerOption("casper");
            options.registerOption("today",true);
            
            options.setOptionList(args);
            
            System.out.println("Value for test: " + options.getOption("test").getValue());
            System.out.println("Value for today: " + options.getOption("today").getValue());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
}