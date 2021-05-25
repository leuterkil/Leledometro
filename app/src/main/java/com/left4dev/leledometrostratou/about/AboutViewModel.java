package com.left4dev.leledometrostratou.about;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

import com.left4dev.leledometrostratou.R;

import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {


    public AboutViewModel() {
    }

    public Intent sendMail(String email)
    {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"+email));
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mail Subject");
        intent.putExtra(Intent.EXTRA_TEXT   , "message");
        intent.setPackage("com.google.android.gm");
        return intent;
    }
    public Intent openLink(String link) {
       Uri uri = Uri.parse("http://www."+link); // missing 'http://' will cause crashed
       Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return intent;
   }
}