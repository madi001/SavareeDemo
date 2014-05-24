package com.demo.savareedemo;

/**
 * Created by madi on 3/2/14.
 */
public class ReviewListModel {
    private  String Name = "";
    private  String comments = "";

    /*********** Set Methods ******************/

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /*********** Get Methods ****************/

    public String getName()
    {
        return this.Name;
    }

    public String getComments()
    {
        return this.comments;
    }
}
