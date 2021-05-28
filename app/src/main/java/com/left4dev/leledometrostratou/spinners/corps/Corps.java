package com.left4dev.leledometrostratou.spinners.corps;

import com.left4dev.leledometrostratou.R;

public class Corps {

    private String[] Titles;
    private int[] Images;
    //TODO : Add 5fh aerometaferomenoi

    public Corps() {
        Titles = new String[]{"Πεζικό", "Τεθωρακισμένα", "Πυροβολικό", "Καταδρομείς", "Πεζοναύτες",
                "Διαβιβάσεις", "Μηχανικό", "Στρατονομία", "Εφοδιασμού Μεταφορών", "Υλικού Πολέμου", "Έρευνας και Πληροφορικής"
                , "Τεχνικό", "Υγειονομικό", "Ταχυδρομικό", "Προεδρική Φρουρά", "Οικονομικό", "Μουσικό", "Γεωγραφικό"
                , "Εθνοφυλακή", "ΕΛΔΥΚ", "Πολεμική Αεροπορεία", "71η Αερομεταφερόμενοι"};

        Images = new int[]{R.mipmap.ic_peziko, R.mipmap.ic_tethorakismena, R.mipmap.ic_piroboliko,
                R.mipmap.ic_katadromeis, R.mipmap.ic_pezonautes, R.mipmap.ic_db, R.mipmap.ic_mixaniko, R.mipmap.ic_stratonomia,
                R.mipmap.ic_efodiasmou, R.mipmap.ic_yp, R.mipmap.ic_ep, R.mipmap.ic_texniko, R.mipmap.ic_ygeonomiko,
                R.mipmap.ic_taxydromiko, R.mipmap.ic_proedrikifroura, R.mipmap.ic_oikonomiko, R.mipmap.ic_mousiko,
                R.mipmap.ic_geografiko, R.mipmap.ic_ethnofilaki, R.mipmap.ic_eldyk, R.mipmap.ic_aeroporeia, R.mipmap.ic_soam};
    }

    public void setTitles(String[] titles) {
        Titles = titles;
    }

    public void setImages(int[] images) {
        Images = images;
    }

    public int[] getImages() {
        return Images;
    }

    public String[] getTitles() {
        return Titles;
    }

    public int getImage(int i) {
        return Images[i];
    }

    public String getTitle(int i) {
        return Titles[i];
    }
}
