package com.mygdx.game;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by user on 26.1.2017.
 */
public class User {
    public enum user_type{
        BIL,NRM
    }

    public boolean isActive;
    public user_type Type;
    public int age;
    public String Name,Uname, Password, E_mail,about,gender;
    public Date  registered;
    public long latitude,longitude,user_id;
    public User [] friends;
    public File picture;


    public User()
    {
        isActive= false;
        user_id= 0;
        Name= null;
        Uname= null;
        Password= null;
        E_mail= null;
        Type= null;
        about=null;
        gender=null;
        registered=null;
        latitude=0;
        longitude=0;
        friends=null;
        picture=null;
    }

    public User(String name,String password,String mail,user_type type)
    {
        Name= name;
        Password= password;
        E_mail= mail;
        Type= type;
        LocalDateTime now = LocalDateTime.now();
        registered=new Date();

    }

    public void setUser_id(int user_id){this.user_id=user_id;}
    public void setType(user_type type)
    {
        Type=type;
    }
    public void setName(String x)
    {
        Name=x;
    }
    public void setUname(String x) {Uname=x;}
    public void setPassword(String x)
    {
        Password=x;
    }
    public void setMail(String x)
    {
        E_mail=x;
    }

    public String getName() {return Name; }
    public String getUname() {return Uname; }
    public String getPassword() {return Password; }
    public String getE_mail() {return E_mail; }
    public user_type getType(){return Type;}


}
