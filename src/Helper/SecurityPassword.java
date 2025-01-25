package Helper;

public class SecurityPassword {

	
	public static String encryptPassword(String s)
	{
		
		String h = "";
		
		
		
		for(int i=0; i<s.length(); i++)
		{
			int v = 0;
			
			v  += s.charAt(i);
			v  += ( i + s.length() ) ;
			v  += ( s.length() );
			
			h  += (char)v;
		}
		
		return h;
		
	}
	
	public static String decryptPassword(String h) {
		
        String originalPassword = "";
        
       
        int length = h.length();
        
        for (int i = 0; i < length; i++) {
        	
            char encryptedChar = h.charAt(i);
            
            int v = encryptedChar; 
            
           
            v -= (i + length); 
            
            v -= length;  
            
            char originalChar = (char) v; 
            
            originalPassword+=(originalChar);  
        }

        return originalPassword.toString();  
    }
	
}
