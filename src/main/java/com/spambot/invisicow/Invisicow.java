/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spambot.invisicow;

import javax.swing.JOptionPane;

/**
 *
 * @author robin
 */
public class Invisicow {
    public static void main(String[] args) throws InterruptedException {
     
      boolean accessOpt = false;  
      String message;
      int opt  = 0;

       
      while(!accessOpt)
      {
        message =  JOptionPane.showInputDialog(null,"How many invisible cows shall I find for you?"); 
        if(message == null)
        {
            int quitter = JOptionPane.showConfirmDialog(null, "Are you sure you want to give up?", "Whoa there!", JOptionPane.YES_NO_OPTION);
            if(quitter == JOptionPane.YES_OPTION)System.exit(0);
            else continue;
        }
        try
        {
            opt = Integer.parseInt(message);
            if (opt <= 10)
            {
                accessOpt = true;
            }else
            {
                JOptionPane.showMessageDialog(null, "Calm down, your number cannot be larger than 10");
            }
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid number plz");
        }
      }//end while
      
      
      JOptionPane.showMessageDialog(null,"You got it! "+opt+" invisible cows to find.");  
      
          
      SeleniumDriver driver = new SeleniumDriver();
      driver.drive(opt);
    }
    
}
