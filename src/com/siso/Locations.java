package com.siso;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer,Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        try(BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
            BufferedWriter dirFile = new BufferedWriter(new FileWriter("locations.txt"));
        ){
            for (Location location : locations.values()){
                locFile.write(location.getLocationID() + "," + location.getDescription() +"\n");
                HashMap<Location.exitTypes, Integer > exits = new HashMap<>(location.getExits());
                    for(Location.exitTypes exit : exits.keySet()){
                       dirFile.write(location.getLocationID() + "," + exit + "," + location.getExits().get(exit).toString() + "\n");
                    }
            }
        }}


    static {
        //Read locations
        try(BufferedReader dirFile = new BufferedReader(new FileReader("locations.txt"))) {
            String input;
            while ((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
                System.out.println("Imported loc: " + loc + " " + description);
                Map<Location.exitTypes, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Now read exits
        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions.txt"))){
            String input;
            while((input = dirFile.readLine()) != null){
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + " : " + destination);
                Location location = locations.get(loc);
                Location.exitTypes exitType = location.promptForMove(direction);
                location.addExit(exitType, destination);
            }
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}


//static {
//            HashMap<Location.exitTypes, Integer> exitTemp = new HashMap<>();
//            Location location = new Location(0, "EXIT", exitTemp);
//
//            locations.put(0, location);
//
//            exitTemp = new HashMap<>();
//            location = new Location(1, "PC", exitTemp);
//            location.addExit(Location.exitTypes.NORTH, 4);
//            location.addExit(Location.exitTypes.SOUTH, 2);
//            location.addExit(Location.exitTypes.WEST, 3);
//            location.addExit(Location.exitTypes.EAST, 5);
//            locations.put(1, location);
//
//            exitTemp = new HashMap<>();
//            location = new Location(2, "Room", exitTemp);
//            location.addExit(Location.exitTypes.NORTH, 1);
//            location.addExit(Location.exitTypes.WEST, 3);
//            locations.put(2, location);
//
//            exitTemp = new HashMap<>();
//            location = new Location(3, "River", exitTemp);
//            location.addExit(Location.exitTypes.EAST, 1);
//            locations.put(3, location);
//
//            exitTemp = new HashMap<>();
//            location = new Location(4, "Mountain", exitTemp);
//            location.addExit(Location.exitTypes.SOUTH, 1);
//            location.addExit(Location.exitTypes.WEST, 3);
//            locations.put(4, location);
//
//            exitTemp = new HashMap<>();
//            location = new Location(5, "Path", exitTemp);
//            location.addExit(Location.exitTypes.WEST, 1);
//            location.addExit(Location.exitTypes.NORTH, 0);
//            locations.put(5, location);
//        }