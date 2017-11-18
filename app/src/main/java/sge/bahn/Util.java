package sge.bahn;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 21.10.2017.
 */

public class Util {

    static public String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
    }

    public final static int getResourceID(final String resName, final String resType, final Context ctx)
    {
        //System.out.println("getResourceIDName: " + resName);
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            System.out.println("no id found for " + resName);
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            //System.out.println("getResourceID: " + ResourceID);
            return ResourceID;
        }
    }


    private static File getFileFromPath(String fileName) {
        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);
        return new File(resource.getPath());
    }


    public static String readFile(String path, Charset encoding) {
        File file = getFileFromPath(path);

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
