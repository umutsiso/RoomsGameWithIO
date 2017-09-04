package com.siso;

import java.util.Scanner;

public class Main {
    private static Scanner s;
    private static Locations locations = new Locations();
    public static void main(String[] args) {


        boolean run = true;
        System.out.println("HERE");
        Location currentLocation = locations.get(1);
        while(run){
            System.out.println("Current location is " + currentLocation.getDescription());
            String userInput= currentLocation.lookForValidDirection();
            System.out.println(userInput);
            if (userInput ==null){
                continue;
            }
            Location.exitTypes exit = currentLocation.promptForMove(userInput);
            if(!currentLocation.hasExits(exit)){
                System.out.println("no such exit exists !");
                continue;
            } else {
                if (exit.equals(Location.exitTypes.QUIT)){
                    System.out.println("See you next time !");
                    run = false;
                } else {
                    int locationID = currentLocation.getExits().get(exit);
                    System.out.println(locationID);
                    currentLocation = locations.get(locationID);
                    if(locationID ==0){
                        System.out.println("See you next time !");
                        run = false;
                    }
                }

            }

        }






	// write your code here
    }




}
