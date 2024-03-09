/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import util.Input;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Menu {   
    public int getChoice(ArrayList options)
    {        
        System.out.println("HOTEL MANAGEMENT MENU - By Hoang Quoc Hung SA170098 - made with love.");
        for(int i =0;i<options.size();i++)
        {
            System.out.println((i+1)+". "+options.get(i));
        }
        int choice= Input.inputInt("Choose : ",1, options.size());
        return choice;
    }
}
