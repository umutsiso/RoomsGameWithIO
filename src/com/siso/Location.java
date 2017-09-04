package com.siso;

import java.util.*;

public class Location {

    private final int locationID;
    private final String description;
    private final Map<exitTypes, Integer> exits;
    private Scanner s;
    public enum exitTypes{
        WEST,
        EAST,
        NORTH,
        SOUTH,
        QUIT,
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Location(int locationID, String description, Map<exitTypes, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        this.exits = exits;
        this.addExit(exitTypes.QUIT, 0);
    }


    public void addExit(exitTypes exitName, Integer exitNo){
        exits.put(exitName, exitNo);
    }

    public boolean hasExits(exitTypes exit){
        return exits.containsKey(exit);
    }



    public HashMap<exitTypes, Integer> getExits(){
        return new HashMap<>(exits);
    }



    public exitTypes promptForMove(String input){

            if(input.toUpperCase().equals("Q") || input.toUpperCase().equals("QUIT")){
                return exitTypes.QUIT;
            } else if(input.toUpperCase().equals("W") || input.toUpperCase().equals("WEST")){
                return exitTypes.WEST;
            } else if(input.toUpperCase().equals("E") || input.toUpperCase().equals("EAST")){
                return exitTypes.EAST;
            } else if(input.toUpperCase().equals("N") || input.toUpperCase().equals("NORTH")) {
                return exitTypes.NORTH;
            } else if(input.toUpperCase().equals("S") || input.toUpperCase().equals("SOUTH")) {
                return exitTypes.SOUTH;
            } else {
                System.out.println("No such exit !");
                return null;
            }
    }

    public String lookForValidDirection(){
        s = new Scanner(System.in);
        try{
            String input = s.nextLine();
            if (input.length() == 1){
                return input;
            }
            String[] x = input.split(" ");
            for (String str : x){
                if (str.toUpperCase().equals("NORTH")){
                    return "N";
                } else if (str.toUpperCase().equals("SOUTH")){
                    return "S";
                } else if (str.toUpperCase().equals("EAST")) {
                    return "E";
                } else if (str.toUpperCase().equals("WEST")) {
                    return "W";
                } else if (str.toUpperCase().equals("QUIT")) {
                    return "Q";
                }
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
            System.out.println("No such element");
        }
        return null;
    }



}
