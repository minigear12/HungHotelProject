/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Input {
//====================== SCANNER AND y/n CHOICE ============================================================================//
    public static Scanner sc = new Scanner(System.in);
    
    public static String inputYesNoStr(String msg, String yn)
    {
        String data = null;
        do
        {
            System.out.print(msg);
            data = sc.nextLine();
        }
        while(!data.matches(yn));
        return data;
    }
//===========================================================================================================================// 
    
    
//======================== INPUT FOR ADDING HOTEL ===========================================================================//    
    public static int inputInt(String msg, int min, int max)
    {
        int data = 0;
    try
    {
        do{
       
            System.out.print(msg);
            data = Integer.parseInt(sc.nextLine());
            if(data<min||data>max)
            {
                System.out.println("Out of range. Try again !");
            }
        
      }while(data<min||data>max);
    }
    catch(NumberFormatException e)
        {
            System.out.println("This is not an integer ! Please try again.");
            return inputInt(msg, min, max);
        }
        
        return data;
    }
    public static String inputStr(String msg){
        String data;        
            System.out.print(msg);
            data= sc.nextLine().trim();       
        return data;
    }
    
    public static String inputNonBlankStrId(String msg)
    {
        String data;
        
        
            System.out.print(msg);
            data=sc.nextLine().trim();
            Pattern pattern = Pattern.compile("^[H]{1}\\d{2}$");
            Matcher matcher = pattern.matcher(data);
            if(data.length()<1)
            {
                return data;
            }
            if(matcher.matches()==false)
            {
                System.out.println("ID must have \"H\" letter at beginning and has two digits at end.");
                return inputNonBlankStrId(msg);
            }
        
        return data;
    }
    
    public static String inputNonBlankStr(String msg)
    {
        String data;        
            System.out.print(msg);
            data=sc.nextLine().trim();
            if(data.isEmpty())
            {
                System.out.println("This can't be null");
                return inputNonBlankStr(msg);
            }
            Pattern pattern = Pattern.compile("[^;]+");
            Matcher matcher = pattern.matcher(data);
            if(matcher.matches()==false)
            {
                System.out.println("You cannot use the semicolon (;) in this input. ");
                return inputNonBlankStr(msg);
            }              
        return data;
    }
    
    public static String inputNonBlankStrName(String msg)
    {
        String data;        
            System.out.print(msg);
            data=sc.nextLine().trim();
            if(data.isEmpty())
            {
                System.out.println("This can't be null");
                return inputNonBlankStrName(msg);
            }
            Pattern pattern = Pattern.compile("^[A-Z]+[a-zA-Z0-9]+$");
            Matcher matcher = pattern.matcher(data);
            if(matcher.matches()==false)
            {
                System.out.println("You cant start with non-capital letter or having non letter/number afterwards with no space or special character in this input. ");
                return inputNonBlankStrName(msg);
            }
        return data;
    }
    
    public static String inputStarRating(String msg, int min, int max)
    {
        String data = null;
        int star;
        try
        {
            do
        {
            System.out.print(msg);
            data=sc.nextLine().trim();
            star=Integer.valueOf(data);
            if(star<min||star>max)
            {
                System.out.println("Star rating must be from "+min+" star to "+max+" stars");
            }
        }
        while(star<min||star>max);
        }
        catch(NumberFormatException e)
        {
            System.out.println("This is not an Integer. Please try again !!!");
            return inputStarRating(msg, min, max);
        }
        
            return data;        
    }
    
    public static String inputPhoneNumber(String msg)
    {
        String data;
        do
        {
            System.out.print(msg);
            data=sc.nextLine().trim();
            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(data);
            if(data.length()<1)
            {
                System.out.println("This cannot be null");
            }
            if(matcher.matches()==false)
            {
                System.out.println("Phone number must have 10 digits long");
                return inputPhoneNumber(msg);
            }
        }
        while(data.length()<1);        
        return data;
    }
//=======================================================================================================================================//



    
//============================== INPUT FOR UPDATING HOTEL ===============================================================================//
public static String updateInt(String msg, int min, int max)
    {
        String data;
        int dataInt;
    try
    {
        System.out.print(msg);
        data = sc.nextLine().trim();
        if(data.isEmpty())
        {
            return data;
        }
        do{            
            dataInt = Integer.valueOf(data);
            if(dataInt<min||dataInt>max)
            {
                System.out.println("Out of range. Try again !");
            }
      }while(dataInt<min||dataInt>max);
    }
    catch(NumberFormatException e)
        {
            System.out.println("This is not an integer ! Please try again.");
            return updateInt(msg, min, max);
        }
        
        return data;
    }

    public static String updateNonBlankStrName(String msg)
    {
        String data;        
            System.out.print(msg);
            data=sc.nextLine().trim();
            if(data.isEmpty())
            {
                return data;
            }
            Pattern pattern = Pattern.compile("^[A-Z]+[a-zA-Z0-9]+$");
            Matcher matcher = pattern.matcher(data);
            if(matcher.matches()==false)
            {
                System.out.println("You can't start with a non-captial and no space or special characters afterwards");
                return updateNonBlankStrName(msg);
            }              
        return data;
    }
    
    public static String updateNonBlankStr(String msg)
    {
        String data;                
            System.out.print(msg);
            data=sc.nextLine().trim();
            if(data.isEmpty())
            {
                return data;
            }
            Pattern pattern = Pattern.compile("[^;]+");
            Matcher matcher = pattern.matcher(data);
            if(matcher.matches()==false)
            {
                System.out.println("You cannot use semicolon (;) in this input.");
                return updateNonBlankStr(msg);
            }
            return data;           
    }
    
    public static String updateStarRating(String msg, int min, int max)
    {
        String data = null;
        int star;
        try
        {
            do
        {
            System.out.print(msg);
            data=sc.nextLine().trim();
            if(data.isEmpty())
            {
                return data;
            }
            star=Integer.valueOf(data);
            if(star<min||star>max)
            {
                System.out.println("Star rating must be from "+min+" star to "+max+" stars");
            }
        }
        while(star<min||star>max);
        }
        catch(NumberFormatException e)
        {
            System.out.println("This is not an Integer. Please try again !!!");
            return inputStarRating(msg, min, max);
        }        
            return data;        
    }
    
    public static String updatePhoneNumber(String msg)
    {
        String data;
        
            System.out.print(msg);
            data=sc.nextLine().trim();
            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(data);
            if(data.length()<1)
            {
                return data;
            }
            if(matcher.matches()==false)
            {
                System.out.println("Phone number must have 10 digits long");
                return inputPhoneNumber(msg);
            }
            return data;
    }
    
    //====================================== INPUT FOR SEARCHING HOTEL NAME ==================================================================//
    public static String searchNonBlankStrName(String msg)
    {
        String data;        
            System.out.print(msg);
            data=sc.nextLine().trim();
            if(data.isEmpty())
            {
                System.out.println("This can't be null");
                return searchNonBlankStrName(msg);
            }
        return data.toLowerCase();
    }
    //========================================================================================================================================//
}
//========================================================================================================================================//


