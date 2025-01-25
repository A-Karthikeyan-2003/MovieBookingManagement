package Helper;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationManagement {

	
	public static boolean validateDate(String datesForShow) throws NumberFormatException {
		
		try {

		
	datesForShow = datesForShow.trim();
	int c=0;
	for( int i=0; i<datesForShow.length(); i++ )
	{ if( i== 4 || i== 7) { continue; }
		if( !( Integer.parseInt( String.valueOf( datesForShow.charAt(i) ) )>=0 &&  Integer.parseInt( String.valueOf( datesForShow.charAt(i) ) ) <=9 ) )
		{	c=-1;
			break;
		}
	}
	if( c<0 ) { System.out.println("Enter Valid Date Format");	
	return false; }
	if ( !( datesForShow.length() == 10 && datesForShow.charAt(4) == '-' && datesForShow.charAt(7) == '-' ) ) {
	
		
		System.out.println("Enter Valid Date Format");	
		return false;
	}
	
	
	return true;
	
		}
		catch(Exception e) { System.out.println(e.getMessage());  return false; }
}
	
	
	public static boolean validateDateAfter(String datesForShow) throws NumberFormatException, ParseException {
	
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    Date date1 = simpleDateFormat.parse(datesForShow);
	    LocalDate date = LocalDate.now();
	    Date date2 = simpleDateFormat.parse(String.valueOf(date));


	  if( date1.after(date2) ) { System.out.println("you can not add future date"); return false; } 
		
		return true;
		
	}
	
	public static boolean validateDateBefore(String datesForShow) throws NumberFormatException, ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    Date date1 = simpleDateFormat.parse(datesForShow);
	    LocalDate date = LocalDate.now();
	    Date date2 = simpleDateFormat.parse(String.valueOf(date));


	  if( date1.before(date2) ) { System.out.println("you can not add past date"); return false; } 
		
		return true;
	
	}

	
	
	//============================================================================================================================
	
	
	public static boolean validateTime(String time) throws NumberFormatException {
	
		try { 
		time = time.trim();
		
	int c=0;
	for( int i=0; i<time.length(); i++ )
	{
		if( i== 2 ) { continue; }
		if( !( Integer.parseInt( String.valueOf( time.charAt(i) ) ) >= 0 &&  Integer.parseInt( String.valueOf( time.charAt(i) ) ) <= 9 ) )
		{	
			c=-1;
			break;
		}
	}
	if( c<0 ) { System.out.println("Enter Valid Time Format");	
	return false; }
	if ( !( time.length() == 5 && time.charAt(2) == ':'  ) ) {
	
		
		System.out.println("Enter Valid Time Format");	
		return false;
	}
	
	
	return true;
	
		}
		catch(Exception e) { System.out.println(e.getMessage());  return false;}
}
	
	
	
	//============================================================================================================================
	
	
	
	public static boolean validateInput(String s) {
		
		try {
			
			if(s.isEmpty())
			
			{ System.out.println("Input Can not be empty");
			
			return false; 
			
			} 
			
			s = s.trim();
			
			int c=0;
			
			for(int i=0; i<s.length(); i++)
			{
				if( Character.isLowerCase( s.charAt(i) ) ) {
					
					if( s.charAt(i) >= 97 && s.charAt(i) <= 122 )
					{
						c++;
					}
				}
				else if( Character.isUpperCase( s.charAt(i) ) ) {
					if(  s.charAt(i) >= 65 && s.charAt(i) <= 90 )
					{
						c++;
					}
				}
			
			}
			
			if( c == s.length() ) { 
				
				return true;
				
			}
			else {
				
				System.out.println("Please Enter Text value only"); 
				System.out.println("Your input can contain space and letters.. pls Enter valid input.."); 
				return false; 
				
			}
		
		} 
		
		catch(Exception exception)
		
		{ 
			System.out.println("Please Enter Text value only");
			return false;
		
		}
		
		
		
	}
	
	
	
	
	public static boolean validateInput(int s) {
		
		try {
			
			int ss = s; 
			
			} 
		
		catch(InputMismatchException exception)
		{ 
			System.out.println("Please Enter Numerical value");
			return false;
			
		}
		return true;
	}
	
	
	public static boolean validateEmail(String email)
	{
		
		if( !email.contains("@") || !email.contains(".") ) { System.out.println("Enter valid email"); return false; }
		
		
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; 
		Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(email);  
        
		return  matcher.matches();
	}


	public static boolean validateContact(long contact1) {
     
		String contact = String.valueOf(contact1);
		
		if( contact.length()!=10 ) { System.out.println("Mobile number must be 10 digit"); } 

        String r = "^[7-9][0-9]{9}$";

        if (contact.matches(r)) {
        	return true;
        }
        else {
            
            return false;
        }
	
		
	}
	
	private static boolean checkString(String str) {
	    char ch;
	    
	    boolean capitalFlag = false;
	    
	    boolean lowerCaseFlag = false;
	    
	    boolean numberFlag = false;
	    
	    for(int i=0;i < str.length();i++) {
	    	
	        ch = str.charAt(i);
	        
	        if( Character.isDigit(ch)) {
	        	
	            numberFlag = true;
	            
	        }
	        else if (Character.isUpperCase(ch)) {
	        	
	            capitalFlag = true;
	            
	        } else if (Character.isLowerCase(ch)) {
	        	
	            lowerCaseFlag = true;
	        }
	        
	        if(numberFlag && capitalFlag && lowerCaseFlag) {
	        	
	            return true; }
	    }
	    return false;
	}
	
	public static boolean validatePassword(String s) {
		
		if( s.length() < 8 && s.length() > 15 )
		{
			System.out.println("Password length must be greter than 8 and also less than 15 ");
			return false;
		}
		
	if( !checkString(s) ) { System.out.println("Password must contain one Uppercase, lowerCase , digit "); return false; }
		
		return true;
		
		
	
	}
	
	
	public static boolean validateSeat(String s)
	{
		if( ( s.length() !=3 ) || s.charAt(1) !='-' ) { System.out.println("Enter valid format..1"); return false; }
		
		String[] st = s.split("-");
		
		char c = st[0].charAt(0);
		int c1 = Integer.parseInt( String.valueOf( st[1].charAt(0) ) );
		if( !( c >=65 && c <=91 )   ) { System.out.println("Enter valid format..2"); return false; }
		if( !(c1>=1 && c1 <=9 ) ) { System.out.println("Enter valid format..3"); return false; }
		return true;
		
	}
	
	
	
	
	
}
