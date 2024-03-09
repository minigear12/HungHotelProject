/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class Hotel {
    private String hotelId;
    private String hotelName;
    private int hotelRoom;
    private String hotelAddress;
    private String hotelPhone;
    private int hotelRating;

    Hotel(String hotelId, String hotelName, int hotelRoom, String hotelAddress, String hotelPhone, int hotelRating) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelRoom = hotelRoom;
        this.hotelAddress = hotelAddress;
        this.hotelPhone = hotelPhone;
        this.hotelRating = hotelRating;
    }
      
    protected String getHotelId() {
        return hotelId;
    }

    protected void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    protected String getHotelName() {
        return hotelName;
    }

    protected void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    protected int getHotelRoom() {
        return hotelRoom;
    }

    protected void setHotelRoom(int hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    protected String getHotelAddress() {
        return hotelAddress;
    }

    protected void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    protected String getHotelPhone() {
        return hotelPhone;
    }

    protected void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    protected int getHotelRating() {
        return hotelRating;
    }

    protected void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s star", hotelId, hotelName, hotelRoom, hotelAddress, hotelPhone, hotelRating);
    }
}
