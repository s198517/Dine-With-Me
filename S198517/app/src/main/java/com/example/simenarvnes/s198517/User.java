package com.example.simenarvnes.s198517;

/**
 * Created by simenarvnes on 04/11/15.
 */
public class User {

    private int id;
    private String _sirname;
    private String _lastname;
    private String _address;
    private String _zip;
    private String _username;
    private String _password;
    private int _rating;

    public User(int id, String sirname, String lastname, String address, String zip, String username, String password, int rating){
        this.id = id;
        _sirname = sirname;
        _lastname = lastname;
        _address = address;
        _zip = zip;
        _username = username;
        _password = password;
        _rating = rating;
    }

    public User(String sirname, String lastname, String address, String zip, String username, String password){
        _sirname = sirname;
        _lastname = lastname;
        _address = address;
        _zip = zip;
        _username = username;
        _password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get_sirname() {
        return _sirname;
    }

    public void set_sirname(String _sirname) {
        this._sirname = _sirname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_zip() {
        return _zip;
    }

    public void set_zip(String _zip) {
        this._zip = _zip;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

}
