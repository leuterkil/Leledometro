package com.left4dev.leledometrostratou.recyclerviews.ranks;

import com.left4dev.leledometrostratou.R;

public class RanksDatas {

    private String[] Names;
    private int[] Images;

    public RanksDatas() {
        Names = new String[]{"Υποδεκανέας", "Δεκανέας", "Λοχίας", "Επιλοχίας", "Αρχιλοχίας", "Ανθυπασπιστής",
                "Δ.Ε.Α", "Ανθυπολοχαγός", "Υπολοχαγός", "Λοχαγός", "Ταγματάρχης", "Αντισυνταγματάρχης", "Συνταγματάρχης",
                "Ταξίαρχος", "Υποστράτηγος", "Αντιστράτηγος", "Στρατηγός"};
        Images = new int[]{R.drawable.diakritiko_stratos_xiras_ypodekaneas, R.drawable.diakritiko_stratos_xiras_dekaneas
                , R.drawable.diakritiko_stratos_xiras_loxias, R.drawable.diakritiko_stratos_xiras_epiloxias
                , R.drawable.diakritiko_stratos_xiras_arxiloxias, R.drawable.diakritiko_stratos_xiras_antipaspistis
                , R.drawable.diakritiko_stratos_xiras_dea, R.drawable.diakritiko_stratos_xiras_anthipoloxagos
                , R.drawable.diakritiko_stratos_xiras_ypoloxagos, R.drawable.diakritiko_stratos_xiras_loxagos
                , R.drawable.diakritiko_stratos_xiras_tagmatarxis, R.drawable.diakritiko_stratos_xiras_antisintagmatarxis
                , R.drawable.diakritiko_stratos_xiras_sintagmatarxis, R.drawable.diakritiko_stratos_xiras_taxiarxos
                , R.drawable.diakritiko_stratos_xiras_ypostratigos, R.drawable.diakritiko_stratos_xiras_antistratigos,
                R.drawable.diakritiko_stratos_xiras_stratigos
        };
    }

    public String[] getNames() {
        return Names;
    }

    public int[] getImages() {
        return Images;
    }
}
