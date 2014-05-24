package com.demo.savareedemo;

/**
 * Created by madi on 2/28/14.
 */
public class ListModel {
    private  String Name="";
    private  String seats="";
    private  String address="";
    private  String rides="";
    private  String image="";

    /*********** Set Methods ******************/

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setSeats(String seats)
    {
        this.seats = seats;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setRides(String rides)
    {
        this.rides = rides;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    /*********** Get Methods ****************/

    public String getName()
    {
        return this.Name;
    }

    public String getSeats()
    {
        return this.seats;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getRides()
    {
        return this.rides;
    }

    public String getImage()
    {
        return this.image;
    }
}
