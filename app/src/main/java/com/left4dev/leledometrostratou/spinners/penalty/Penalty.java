package com.left4dev.leledometrostratou.spinners.penalty;

public class Penalty {

    private String[] Type;


    public Penalty()
    {
        Type = new String[]{"Φυλακή","Στέρηση Εξόδου"};
    }


    public String getType(int i)
    {
        return Type[i];
    }

    public void setTypes(String[] type) {
        Type = type;
    }

    public String[] getTypes() {
        return Type;
    }
}
