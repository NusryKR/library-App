package com.example.public_library;



public class Users {

    private String address;
    private String name;
    private String email;
   // private String password;


    public String getAddress() {
        return address;
    }

    public void setEpf(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public String getPassword() {
        return password;
    }*/

  /*  public void setPassword(String password) {
        this.password = password;
    }*/

    public Users( String name, String email, String address) {

        this.address = address;
        this.name = name;
        this.email = email;
       // this.password = password;

    }
}
