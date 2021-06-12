package com.left4dev.leledometrostratou.tablists;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.left4dev.leledometrostratou.MainActivity;
import com.left4dev.leledometrostratou.R;

import java.io.File;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

public class ListOfVacationViewModel extends ViewModel {


    public AlertDialog confirm(Context context, Activity activity) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title

        alertDialogBuilder.setTitle("Είσαι Σίγουρος;");
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_delete_forever_24);

        // set dialog message
        alertDialogBuilder
                .setMessage("Πρόκειται να διαγραφούν όλες σου οι άδειες.\n Διαγραφή?")
                .setCancelable(false)
                .setPositiveButton("Επιβεβαίωση",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        File f = new File(context.getString(R.string.vacations_path));
                        f.delete();
                        Intent intent = new Intent(context, MainActivity.class);
                        Toast.makeText(context,"Επιτυχής Διαγραφή",Toast.LENGTH_LONG).show();
                        context.startActivity(intent);
                        activity.finish();

                    }
                })
                .setNegativeButton("Ακύρωση",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        return alertDialog;


    }
}