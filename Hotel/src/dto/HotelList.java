/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import util.Input;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class HotelList extends ArrayList<Hotel>
{
    
//============================  [FILE CONSTRUCTOR]  ========================================================//
    public HotelList(String filename) throws FileNotFoundException, IOException
    {
        int hotelRoomInt;
        int starInt;        
        try{
           File f = new File(filename);
           Scanner sc = new Scanner(f);
           
           
           
           while(sc.hasNextLine())
           {
               String data = sc.nextLine();
               
               String[] split = data.split(";");
               String hotelId = split[0];
               String hotelName = split[1];
               String hotelRoom=split[2];
               if(hotelRoom != null && hotelRoom.matches("[0-9]+"))
               {                  
                   hotelRoomInt = Integer.valueOf(hotelRoom);                  
               }
               else
               {
                   hotelRoom="0";
                   hotelRoomInt = Integer.valueOf(hotelRoom);
                   System.out.println("Illegal non integer found, value has been converted to 0");
               }               
               String hotelAddress = split[3];
               String hotelPhone = split[4];
               String hotelRating = split[5];
               hotelRating = hotelRating.replaceAll("[^\\d]", "");
               starInt = Integer.valueOf(hotelRating);
               this.add(new Hotel(hotelId,hotelName,hotelRoomInt,hotelAddress,hotelPhone,starInt));
               
           }
           
           try (FileWriter fw = new FileWriter(f)) {
                
                    // Write data into file
                    this.stream().sorted(Comparator.comparing(Hotel::getHotelId)).forEachOrdered((h)
           ->
           {
                        try {
                            fw.write(h.toString());
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
                        try {
                            fw.write("\n");
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
           });
                    fw.close();
                }
                
            
    
            catch(IOException io)
            {
                System.out.println("IO error, cannot overwrite file.");
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File "+filename+" not found. You can create one by adding a hotel.");
        }   
    }
//==========================================================================================================//



    
//==================== [SEARCH, EXIST AND SCAN POSITION ID] ================================================//
    
    private Hotel searchId(String hotelId)
    {
        if(!this.isEmpty())
        {
            for(Hotel h : this)
            {
                if(h.getHotelId().equals(hotelId))
                {
                    return h;
                }
            }
        }
        return null;
    }
    private boolean existId(String hotelId)
    {
        return searchId(hotelId)!= null;
    }
    private int scanPositionId(String hotelId)
    {
        if(!this.isEmpty())
        {
            for (int h = 0;h<this.size();h++) {
                if (this.get(h).getHotelId().equals(hotelId)) {
                    return h;
                }
            }
        }
        return -1;
    }
//==========================================================================================//   
    

//=================================[SCAN POSITION HOTEL NAME] ===========================================================//
    private int scanPositionName(String hotelName)
    {
        if(!this.isEmpty())
        {
            for (int h = 0;h<this.size();h++) {
                if (this.get(h).getHotelName().toLowerCase().contains(hotelName)) {
                    return h;
                }
            }
        }
        return -1;
    }
//====================================================================================================================//
    public void deleteHotelRating(String filename) throws IOException
    {
        if(this.isEmpty())
        {
            System.out.println("No hotel found");
        }
        else
        {
            String uRating = Input.inputStarRating("Input Star Rating that is less than it to delete : ", 1, 5);
            int uRatingInt = Integer.valueOf(uRating);
            if(Input.inputYesNoStr("Are you sure ? [y/n] : ","[yn]").equals("y"))
            {
                for(int h=0;h<this.size();h++)
            {
                if(this.get(h).getHotelRating()<uRatingInt)
                {
                    this.remove(h);
                    File f = new File(filename);
                    try (FileWriter fw = new FileWriter(f)) {
                
                    // Write data into file
                    this.stream().sorted(Comparator.comparing(Hotel::getHotelId)).forEachOrdered((x)
           ->
           {
                        try {
                            fw.write(x.toString());
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
                        try {
                            fw.write("\n");
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
           });
                    fw.close();
                }
                }
            }
                System.out.println("Delete successfully.");
            }
            else
            {
                System.out.println("Cancelled by user.");
            }
        }
    }
    
    

    
//===================== [ADD HOTEL] ==========================================================================//
public String addHotel (String filename) 
{
    String hotelId = null;
    String hotelName;
    int hotelRoom;
    String hotelAddress;
    String hotelPhone;
    String hotelRating;
    int starInt;
    
  try
    {     
//=================================================================================================================//
  /**do
    {
        hotelId = Input.inputNonBlankStrId("ID (Leave blank to not add hotel) : ");
        if(existId(hotelId))
            System.out.println("ID existed !");
        if(hotelId.length()<1)
       {
        System.out.println("Adding hotel unsuccessfully");
        return filename;
       }
    }
    while(existId(hotelId));**/
        int found = 0;
        int set = 1;
        boolean yes = false;
        try{
           File f = new File(filename);
           Scanner sc = new Scanner(f);
           
           while(sc.hasNextLine())
           {
               String data = sc.nextLine();
               String[] split = data.split(";");
               hotelId = split[0];
               hotelId = hotelId.replaceAll("[^\\d]", "");
               int hotelIdInt = Integer.valueOf(hotelId);
               if(set!=hotelIdInt && !yes)
               {
                       found = set;
                       yes=true;                           
               }
               set++;
           }
           //==========================================
           if(yes)
           {
               if(found<10)
               {
                   hotelId="H0"+String.valueOf(found);
               }
               
               else
               {
                   hotelId="H"+String.valueOf(found);
               }
           }
           
           //==========================================
           
           else
           {
               if(set<10)
               {
                   hotelId="H0"+String.valueOf(set);
               }
               
               else
               {
                   hotelId="H"+String.valueOf(set);
               }
           }
           
           //==========================================
           
           if(Input.inputYesNoStr("ID "+hotelId+" automatically created, do you wish to continue ? [y/n] : ","[yn]").equals("n"))
           {
               System.out.println("Add hotel cancelled.");
               return filename;
           }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found, cannot create hotel.");
        }
//==================================================================================================================//   
            hotelName = Input.inputNonBlankStrName("Hotel Name : ");
            hotelRoom = Input.inputInt("Hotel Room Available (Min 0 - Max 99) : ",0,99);
            hotelAddress = Input.inputNonBlankStr("Hotel Address : ");
            hotelPhone = Input.inputPhoneNumber("Hotel Phone Number : ");
            hotelRating = Input.inputStarRating("Hotel Rating : ",1,5);
            starInt=Integer.valueOf(hotelRating);
    this.add(new Hotel(hotelId,hotelName,hotelRoom,hotelAddress,hotelPhone,starInt));
        File f = new File(filename);
        try (FileWriter fw = new FileWriter(f)) {
                
                    // Write data into file
                    this.stream().sorted(Comparator.comparing(Hotel::getHotelId)).forEachOrdered((h)
           ->
           {
                        try {
                            fw.write(h.toString());
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
                        try {
                            fw.write("\n");
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
           });
                    fw.close();
                }
        if(Input.inputYesNoStr("Do you want to continue adding another Hotel ? [y/n] : ","[yn]").equals("y"))
            this.addHotel(filename);            
    //====================================================================================    
    }
    catch(IOException e)
    {
        System.out.println("IO error ! Cannot add new hotel.");
    }
  return filename;
}
//=============================================================================================================//    
    
    
    
    
    
//===================== [CHECK HOTEL ID] ======================================================================//
    
    public void checkHotelId(String filename)
    {          
        int found = 0;
        try{
           File f = new File(filename);
           Scanner sc = new Scanner(f);
           
           while(sc.hasNextLine())
           {
               String data = sc.nextLine();
               String[] split = data.split(";");
               String hotelId = split[0];
               if(existId(hotelId))
            {
                found++;
            }                      
           }
        if(found==0)
        {
            System.out.println("===================");
            System.out.println("= No Hotel Found. =");
            System.out.println("===================");
        }
        else
        {
            System.out.println("================");
            System.out.println("= Exist Hotel. =");
            System.out.println("================");
            System.out.println("There are ["+found+"] hotel(s) available.");
        }           
           }
    catch(FileNotFoundException e)
        {
            System.out.println("This function cannot be used since the file "+filename+" is not found.");
        }
        Input.inputStr("Press Enter to return to main menu...");
    }
    
//===========================================================================================================//


//=================================== UPDATE HOTEL ==========================================================//
public void updateHotel(String filename){
    String uID;
    String hotelName;
    String hotelRoomString;
    int hotelRoom = 0;
    String hotelAddress;
    String hotelPhone;
    String hotelRating;
    int starInt = 0;
    int name=0,room=0,address=0,phone=0,rating=0;
        if (this.isEmpty()) {
            System.out.println("Hotel is empty.");
        } 
        else {
            uID = Input.inputNonBlankStrId("Hotel ID : ");
            int pos = scanPositionId(uID);
            if (pos < 0) {
                System.out.println("Hotel ID does not exist. Update unsuccessfully");
            } else 
            {       
                    //====================================================================================
                
                    hotelName = Input.updateNonBlankStrName("Hotel Name : ");
                    if(hotelName.isEmpty())
                    {
                        hotelName = this.get(pos).getHotelName();
                        name++;
                    }
                    hotelRoomString = Input.updateInt("Hotel Room Available (Min 0 - Max 99) : ",0,99);
                    if(hotelRoomString.isEmpty())
                    {
                        hotelRoom = this.get(pos).getHotelRoom();
                        room++;
                    }
                    hotelAddress = Input.updateNonBlankStr("Hotel Address : ");
                    if(hotelAddress.isEmpty())
                    {
                        hotelAddress = this.get(pos).getHotelAddress();
                        address++;
                    }
                    hotelPhone = Input.updatePhoneNumber("Hotel Phone Number : ");
                    if(hotelPhone.isEmpty())
                    {
                        hotelPhone = this.get(pos).getHotelPhone();
                        phone++;
                    }
                    hotelRating = Input.updateStarRating("Star Rating : ",1,5);
                    starInt = Integer.valueOf(hotelRating);
                    if(hotelRating.isEmpty())
                    {
                        starInt = this.get(pos).getHotelRating();
                        rating++;
                    }
                    this.set(pos, new Hotel(uID,hotelName,hotelRoom,hotelAddress,hotelPhone,starInt));
                    this.stream().sorted(Comparator.comparing(Hotel::getHotelId).reversed());
                    //====================================================================================
                    
                    String nameResult = (name==1)? "Not changed" : "Changed";
                    String roomResult = (room==1)? "Not changed" : "Changed";
                    String addressResult = (address==1)? "Not changed" : "Changed";
                    String phoneResult = (phone==1)? "Not changed" : "Changed";
                    String ratingResult = (rating==1)? "Not changed" : "Changed";
                    
                    System.out.println("\nRESULT AFTER UPDATING "+uID+" :");
                    System.out.println("Name : "+nameResult);
                    System.out.println("Room Available : "+roomResult);
                    System.out.println("Address : "+addressResult);
                    System.out.println("Phone number : "+phoneResult);
                    System.out.println("Star Rating : "+ratingResult+"\n");
                    
                    //====================================================================================
                    
                    
                    File f = new File(filename);
                    try (FileWriter fw = new FileWriter(f)) {
                for (Hotel h : this) {
                    // Write data into file
                    fw.write(h.toString());
                    fw.write("\n");
                }
                fw.close();
            }
            catch(IOException io)
            {
                System.out.println("IO error, cannot overwrite file.");
            }
                } 
        }           
    }
//==========================================================================================================//

//======================================== DELETE HOTEL ====================================================//
public void deleteHotel(String filename)
{
    if (this.isEmpty()) {
            System.out.println("Hotel is empty.");
        } 
        else {
            String uID = Input.inputNonBlankStrId("Hotel ID : ");
            int pos = scanPositionId(uID);
            if (pos < 0) {
                System.out.println("Hotel ID does not exist. delete unsuccessfully");
            } else
            {
                
                if(Input.inputYesNoStr("Do you wish to delete this hotel with ID : "+uID+" ? [y/n] : ","[yn]").equals("y"))
                {
                    if(Input.inputYesNoStr("Are you REALLY sure to delete this Hotel with ID : "+uID+" ? [y/n] : ","[yn]").equals("y"))
                    {
                        this.remove(pos);
                        
                    File f = new File(filename);
            try (FileWriter fw = new FileWriter(f)) {
                
                    // Write data into file
                    this.stream().sorted(Comparator.comparing(Hotel::getHotelId)).forEachOrdered((h)
           ->
           {
                        try {
                            fw.write(h.toString());
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
                        try {
                            fw.write("\n");
                        } catch (IOException ex) {
                            System.out.println("IO error, cannot overwrite file.");
                        }
           });
                    fw.close();
                }
            catch(IOException io)
            {
                System.out.println("IO error, cannot overwrite file.");
            }               
                System.out.println("Delete ID "+uID+" successfully.");
                    }
                    else
                    {
                        System.out.println("Delete ID "+uID+" unsuccessfully. Cancelled by user.");
                    }
                }
                else
                {
                    System.out.println("Delete ID "+uID+" unsuccessfully. Cancelled by user.");
                }
                
            }
            
}   
}
//============================================================================================================================//

//======================================== SEARCH HOTEL ID ===================================================================//
public void searchHotelId()
{
    if(this.isEmpty())
    {
        System.out.println("Hotel is empty");
    }
    else
    {
        String uID = Input.inputNonBlankStrId("Hotel ID : ");
        int pos=scanPositionId(uID);
        if(pos<0)
        {
            System.out.println("Hotel ID not found. Search unsuccessfully");
        }
        else
        {
            System.out.println("INFORMATION OF HOTEL ID "+uID+" : ");
            System.out.println((String.format("| %-12s | %-5s | %-80s | %-11s | %-6s |","Hotel Name","Rooms","Hotel Address","Phone","Rating")));
            this.stream().filter((x)->(x.getHotelId().equals(uID))).forEachOrdered(
            (x)->
            {
                System.out.println((String.format("| %-12s | %-5d | %-80s | %-11s | %-7s |",x.getHotelName(),x.getHotelRoom(),
                        x.getHotelAddress(),x.getHotelPhone(),x.getHotelRating())));
            }
            );
        }
    }
}
//============================================================================================================================//

//======================================== SEARCH HOTEL NAME ===================================================================//
public void searchHotelName()
{
    if(this.isEmpty())
    {
        System.out.println("Hotel is empty");
    }
    else
    {
        String hotelName = Input.searchNonBlankStrName("Input Hotel Name's Keyword (Case insensitive) : ");
        int pos=scanPositionName(hotelName);
        if(pos<0)
        {
            System.out.println("No Hotel Name Found");
        }
        else
        {
            System.out.println("INFORMATION OF HOTEL(s) WITH THE KEYWORD \""+hotelName+"\" : ");
            System.out.println((String.format("|%-4s | %-12s | %-5s | %-80s | %-11s | %-6s |","ID","Hotel Name","Rooms","Hotel Address","Phone","Rating")));
            this.stream().filter((x)->(x.getHotelName().toLowerCase().contains(hotelName))).sorted(Comparator.comparing(Hotel::getHotelName).reversed()).forEachOrdered(
                    (x)->
                    {
                        System.out.println((String.format("|%-4s | %-12s | %-5d | %-80s | %-11s | %s star |",x.getHotelId(),x.getHotelName(),x.getHotelRoom(),
                        x.getHotelAddress(),x.getHotelPhone(),x.getHotelRating())));
                    }
                    );
        }
    }
}
//========================================================================================================================================================//

//======================================== HOTEL LIST ====================================================================================================//
public void listHotel()
{
    if(this.isEmpty())
    {
        System.out.println("Hotel is empty");
    }
    else
    {
        System.out.println("LIST ON HOTELS IN DESCENDING ORDER BY HOTEL NAME");
        System.out.println((String.format("|%-4s | %-12s | %-5s | %-80s | %-11s | %-6s |","ID","Hotel Name","Rooms","Hotel Address","Phone","Rating")));
        this.stream().sorted(Comparator.comparing(Hotel::getHotelName).reversed()).forEachOrdered(
                (x)->
                {
                    System.out.println((String.format("|%-4s | %-12s | %-5d | %-80s | %-11s | %s star |",x.getHotelId(),x.getHotelName(),x.getHotelRoom(),
                    x.getHotelAddress(),x.getHotelPhone(),x.getHotelRating())));
                }
                );
    }
}
//========================================================================================================================================================//
public void listHotelRoomAvailable()
{
    if(this.isEmpty())
    {
        System.out.println("Hotel is empty");
    }
    else
    {
        System.out.println("LIST OF HOTELS IN DESCENDING ORDER BY ROOM AVAILABLE");
        System.out.println((String.format("|%-4s | %-12s | %-5s | %-80s | %-11s | %-6s |","ID","Hotel Name","Rooms","Hotel Address","Phone","Rating")));
        this.stream().sorted(Comparator.comparing(Hotel::getHotelRoom).reversed()).forEachOrdered(
        (x)->
        {
            System.out.println((String.format("|%-4s | %-12s | %-5d | %-80s | %-11s | %s star |",x.getHotelId(),x.getHotelName(),x.getHotelRoom(),
                    x.getHotelAddress(),x.getHotelPhone(),x.getHotelRating())));
        });
    }
}
}

