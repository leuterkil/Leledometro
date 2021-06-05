package com.left4dev.leledometrostratou.spinners.services;

public class Services {

    private String[] ServicesTypes;
    private String[] Numbers;

    public Services()
    {
        ServicesTypes = new String[]{"Θαλαμοφύλακας","ΑΟΤ","Πυρομαχικά","Κάμερες","ΛΥΛ(Όργανο Υπηρεσίας)","ΔΥΛ","Δεκανέας Αλλαγής"
        ,"Όρχος","ΚΕΛ(Κεντρο Εκπομπής και Λήψεων","ΚΕΠΙΚ(Τηλεφωνιτής)","ΚΕΠΙΚ(Ασυρματιστής)","Αρχιφύλακας Φρουράς"
        ,"Α.Μ","Α.Υ.Δ.Μ","Β.Α.Υ.Δ.Μ","Γραφέας Υπηρεσίας","ΓΕΠ","Περίπολος","Κάμερες","Ραντάρ"
        ,"Νοσοκόμος - Τραυματιοφορέας","Οδηγός Επιφυλακής","Τ.Α.Ε","Ορχήστρα","Μαγειρεία","Εστιατόρια"
        ,"Φυλάκιο","Πλυντήρια","Πυρασφάλεια","Κ.Ψ.Μ","Επόπτης ΚΕΠΙΚ","Άλλη Υπηρεσία"};

        Numbers = new String[]{"1ο Νούμερο","2ο Νούμερο","3ο Νούμερο","4ο Νούμερο","24ωρη"};
    }

    public String[] getServicesTypes()
    {
        return ServicesTypes;
    }
    public String[] getNumbers()
    {
        return Numbers;
    }

    public String getServiceType(int i)
    {
        return  ServicesTypes[i];
    }

    public String getNumber(int i)
    {
        return Numbers[i];
    }

}
