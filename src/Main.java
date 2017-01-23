/*
* Eric Tapia
* Prof. Noga
* Comp496 Fall 2016
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static final String LUC = "Lowest Unused Channels";
    private static final String EFT = "Earliest Finish Time";

    public static void main(String[] args) throws FileNotFoundException{
        //Declarations
        List<List<Integer>> ListOfIntervalsLUC;
        List<List<Integer>> ListOfIntervalsEFT;
        List<List<Integer>> ListOfIntervals = new ArrayList<>();
        List<List<Integer>> ListOfIntervals2 = new ArrayList<>();

        //Prepare for reading file
        File inputFile = new File("Input.txt");
        Scanner input = new Scanner(inputFile);

        try{
            //Read file into arrayList
            while(input.hasNext()){
                ArrayList<Integer> interval = new ArrayList<>();
                //Let interval consist of only start and end times
                for(int col = 0; col < 2; col++){
                    interval.add(input.nextInt());
                }
                ListOfIntervals.add(interval);
            }
            input.close();
            //Create a copy interval list for EFT alg.
            ListOfIntervals2.addAll(ListOfIntervals);

            //Lowest Unused Channels
            ListOfIntervalsLUC = sortIntervals(LUC,ListOfIntervals);
            printChannels(LUC, allocateChannels(ListOfIntervalsLUC));

            //Earliest Finish Time
            ListOfIntervalsEFT = sortIntervals(EFT,ListOfIntervals2);
            printChannels(EFT, allocateChannels(ListOfIntervalsEFT));

        }catch(Exception e){
            System.out.println("Caught Exception: " + e.getMessage());
        }

    }//END MAIN

    //Performs Channel Allocation for a given List of Intervals
    private static List<List<List<Integer>>> allocateChannels(List<List<Integer>> ListOfIntervals){
        List<List<List<Integer>>> listOfChannels = new ArrayList<>();

        //isEmpty() has O(1) constant time
        while(!ListOfIntervals.isEmpty()){
            //Create a new channel for every list of Intervals needed
            List<List<Integer>> Channel = new ArrayList<>();

            //Since ListOfIntervals is sorted - add first interval to channel
            Channel.add(ListOfIntervals.get(0));
            //Remove interval from List
            ListOfIntervals.remove(0);

            //nextrow helps increment the Channel index to point at the next interval
            int nextrow = 0;
            //Check if next interval in list can be added to current Channel
            for(int row = 0; row < ListOfIntervals.size(); row++){
                if(Channel.get(nextrow).get(1) <= ListOfIntervals.get(row).get(0)){

                    Channel.add(ListOfIntervals.get(row));
                    ListOfIntervals.remove(row);
                    nextrow++;
                    row--;
                }
            }
            //Add current Channel to List of Channels
            listOfChannels.add(Channel);
        }
        return listOfChannels;
    }//End allocateChannels

    private static List<List<Integer>> sortIntervals(String algorithmType, List<List<Integer>> listOfIntervals){
        //Java Comparator - nlog(n) performance. Modified mergesort
        if(algorithmType == EFT){
            Collections.sort(listOfIntervals, new Comparator<List<Integer>>(){
                @Override
                public int compare(List<Integer> o1, List<Integer> o2){
                    return o1.get(1).compareTo(o2.get(1));
                }
            });

        } else if(algorithmType == LUC){
            Collections.sort(listOfIntervals, new Comparator<List<Integer>>(){
                @Override
                public int compare(List<Integer> o1, List<Integer> o2){
                    return o1.get(0).compareTo(o2.get(0));
                }
            });

        } else{
            System.out.println("Incorrect Algorithim Type To Sort By.");
        }
        return listOfIntervals;

    }//End sortIntervals

    //Used to print channels for either algorithim
    private static void printChannels(String algorithmType, List<List<List<Integer>>> listOfChannels){
        int chNum = 1;

        System.out.println();
        System.out.println("**" + algorithmType + "**");

        for(List<List<Integer>> ch: listOfChannels){
            System.out.println("Channel " + chNum + ": " + ch);
            chNum++;
        }
    }//End printChannels

}//END CLASS
