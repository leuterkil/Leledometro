package com.left4dev.leledometrostratou.spinners.vacation;

public class Vacations {

    private String[] Type;

    public Vacations()
    {
        Type = new String[]{"Άδεια Ορκομωσίας","Κανονική Άδεια","Τιμητική Άδεια","ΤΑΠ","Φοιτητική","Αναρρωτική Άδεια",
                "Αγροτική Άδεια","Γρουπ Πάσχα","Γρουπ Χριστουγέννων","Γονική Άδεια"};
    }

    public String[] getTypes() {
        return Type;
    }
    public String getType(int i)
    {
        return Type[i];
    }
}
