package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

public class App {
    User user;
    Dish[] dishList=new Dish[15];
    Restro[] restroList=new Restro[5];

    Location[] locationList=new Location[15];

    //Constructor for defining the name of the User and its geographical Location
    App()
    {
        Location userLocation = new Location("000",50,67);
        this.user = new User("garima saha",userLocation);
    }

    public User getUser() {
        return user;
    }

    public void parseRestroData() throws IOException{
        BufferedReader restroData=Files.newBufferedReader(Paths.get("C:\\Users\\garima saha\\testproject\\out\\production\\testproject\\name.csv"));
        String lines=null;
        for(int restcnt=0;(lines=restroData.readLine())!=null;restcnt++){
            String[] data=lines.split(",");
            restroList[restcnt]=new Restro(data[0],data[1],data[2]);
            Dish[] tempMenu=new Dish[3];
            Location tempLocation=null;
            for(int menucnt=0,localcnt=0;(menucnt< dishList.length)&&(dishList[menucnt]!=null);menucnt++){
                if(dishList[menucnt].getRestroID().equals(data[0])) {
                    tempMenu[localcnt] = dishList[menucnt];
                    localcnt++;
                }
            }
            restroList[restcnt].setMenu(tempMenu);

            for(int locationcnt=0;(locationcnt<locationList.length)&&(locationList[locationcnt]!=null);locationcnt++){
                if(locationList[locationcnt].getRestroID().equals(data[0])){
                    tempLocation=locationList[locationcnt];
                }
            }
            restroList[restcnt].setLocation(tempLocation);
        }

    }
    public void parseDishData() throws IOException{
        BufferedReader dishData=Files.newBufferedReader(Paths.get("C:\\Users\\garima saha\\testproject\\out\\production\\testproject\\dish.csv"));
        String lines=null;
        for(int dishcnt=0;(lines=dishData.readLine())!=null;dishcnt++){
            String[] data = lines.split(",");
            dishList[dishcnt]=new Dish(data[0],data[1],data[2],Integer.valueOf(data[3]));
        }

    }
    public void parseLocationData() throws IOException{
        BufferedReader locationData=Files.newBufferedReader(Paths.get("C:\\Users\\garima saha\\IdeaProject\\testproject\\out\\production\\testproject\\location.csv"));
        String lines=null;
        for(int locationcnt=0;(lines=locationData.readLine())!=null;locationcnt++){
            String[] data = lines.split(",");
            locationList[locationcnt]=new Location(data[0],Float.valueOf(data[1]),Float.valueOf(data[2]));
        }
    }

    public double deliveryTime(Location userLocation, Location restroLocation){
        final double speed=10;
        double diff=Math.abs(Math.pow((restroLocation.getLatitude()-userLocation.getLatitude()),2))+Math.abs(Math.pow((restroLocation.getLongitude()-userLocation.getLongitude()),2));
        double ans=Math.sqrt(diff);
        return ans;
    }

    public void createOrder(Restro[] restroList){
        if(restroList==null){
            restroList=this.restroList;
        }

        System.out.println("**********************************************************************");
        System.out.println("Order from Restaurant here");
        Scanner sc=new Scanner(System.in);
        String orderInput=sc.next();
        String[] orderInputData=orderInput.split(",");
        String restroID=orderInputData[0];
        int orderListlength=(orderInputData.length-1)/2;
        Invoice[] orderList=new Invoice[orderListlength];
        int bill=0;
        if(Integer.valueOf(restroID)>5){
            System.out.println("Invalid Input\nPlease enter valid Restaurant ID");
        }
        else{
            System.out.println("**********************************************************************");
            System.out.println("Your Order:");
            System.out.println(restroList[Integer.valueOf(restroID)-1].getRestroName());

            for(int ordercnt=1,orderlistcnt=0;ordercnt<orderInputData.length;ordercnt++){
                Dish[] temp=restroList[Integer.valueOf(restroID)-1].getMenu();
                String dishID=temp[Integer.valueOf(orderInputData[ordercnt])-1].getDishID();
                String dishName=temp[Integer.valueOf(orderInputData[ordercnt])-1].getDishName();
                Dish tempDish=temp[Integer.valueOf(orderInputData[ordercnt])-1];
                int dishPrice=temp[Integer.valueOf(orderInputData[ordercnt])-1].getPrice();
                int qty=Integer.valueOf(orderInputData[++ordercnt]);

                bill+=dishPrice*qty;

                System.out.println("\t"+(orderlistcnt+1)+". "+dishName+ "\tQty: "+qty);
                orderList[orderlistcnt]=new Invoice(tempDish,qty);
                orderlistcnt++;
            }
            System.out.println("**********************************************************************");
            System.out.println("Total : "+bill+"\nMake Payment? Press 1 | YES 2 | NO");
        }
    }


    public void browse() throws NullPointerException {
        System.out.println("**********************************************************************");
        System.out.println("Please choose Dishes from the Following Menu");

        int restrocount=1;
        int dishcount=1;

        for(Restro r:restroList){
            System.out.println("**********************************************************************");
            Dish[] tempMenu=r.getMenu();
            System.out.println(restrocount + ": "+ r.getRestroName() + "\tLocation: "+ r.getRestroAddress()+"\tExpected Delivery Time: " + (int)deliveryTime(this.user.getLocation(),r.getLocation())+" mins");
            for(Dish d:tempMenu){
                System.out.println("\t" + restrocount + "." + dishcount + " " + d.getDishName()+ " "+d.getPrice());
                dishcount++;
            }
            dishcount=1;
            restrocount++;
        }

        createOrder(null);

    }

    public static void main(String []args) throws IOException, InvalidPathException {
        App swiggyApp=new App();
        swiggyApp.parseLocationData();
        swiggyApp.parseDishData();
        swiggyApp.parseRestroData();
        System.out.println("**********************************************************************");
        System.out.println("Hey " +swiggyApp.getUser().getUserName()+" Welcome to our Swiggy App ");
        System.out.println("Press 0 for displaying menu, 1 for searching the menu, 2 for exit");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        if(choice==1){
            swiggyApp.browse();
        }
        else if(choice==2){

        }
        else{
            System.out.println("Invalid Input");
        }

    }
}
