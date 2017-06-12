package com.sstgroup.xabaapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by rosenstoyanov on 6/12/17.
 */

public class Utils {

    public static void openUrl(String url, Context context){

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);

    }

}
