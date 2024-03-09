/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;
import java.util.ArrayList;
import dto.HotelList;
import dto.Menu;
import java.io.IOException;


/**
 *
 * @author Admin
 */

public class HotelManagement{
    public static void main(String[] args) throws IOException
    {
        String hotelData = "Hotel.dat";
        HotelList hlist = new HotelList(hotelData); 
        ArrayList<String> options = new ArrayList<>();
        options.add("Add Hotel");
        options.add("Check for existing Hotel");
        options.add("Update a hotel");
        options.add("Delete a hotel");
        options.add("Search hotel ID");
        options.add("Search hotel name");
        options.add("List hotel in descending order by Hotel name");
        options.add("Delete hotel based on star rating less than it");
        options.add("List hotel in descending order by Room available");
        options.add("Quit");
        Menu menu = new Menu();
        int choice;
        do
        {
            choice=menu.getChoice(options);
            switch(choice)
            {
                case 1:hlist.addHotel(hotelData); break;
                case 2:hlist.checkHotelId(hotelData); break;
                case 3:hlist.updateHotel(hotelData); break;
                case 4:hlist.deleteHotel(hotelData); break;
                case 5:hlist.searchHotelId();break;
                case 6:hlist.searchHotelName();break;
                case 7:hlist.listHotel();break;
                case 8:hlist.deleteHotelRating(hotelData);break;
                case 9:hlist.listHotelRoomAvailable();break;
                default:System.out.println("Goodbye.");
            }
        }
        while(choice>=1 && choice <options.size());
    }
}
    