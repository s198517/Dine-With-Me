package com.example.simenarvnes.s198517;

/**
 * Created by simenarvnes on 04/11/15.
 */
public class Invitation {

    private int _id;
    private String _food;
    private double _budget;
    private String _date;
    private String _time;
    private int _userid;

    public Invitation(String food, double budget, String date, String time, int userid){

        _food = food;
        _budget = budget;
        _date = date;
        _time = time;
        _userid = userid;
    }

    public Invitation(int id, String food, double budget, String date, String time, int userid){

        _id = id;
        _food = food;
        _budget = budget;
        _date = date;
        _time = time;
        _userid = userid;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public double get_budget() {
        return _budget;
    }

    public void set_budget(double _budget) {
        this._budget = _budget;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public int get_userid() {
        return _userid;
    }

    public void set_userid(int _userid) {
        this._userid = _userid;
    }

    public String get_food() {
        return _food;
    }

    public void set_food(String _food) {
        this._food = _food;
    }
}
