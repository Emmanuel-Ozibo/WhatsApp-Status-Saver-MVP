package Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by emmanuel on 27/09/2017.
 */
public class SharedPref{
    public static final String SPLASH_PREF = "splash";
    public static final String HAS_ENTERED = "splash2";
    public static final String NUMBER_ENTERED = "num";

    private static SharedPreferences sharedPreferences(Context context){
        return context.getSharedPreferences(SPLASH_PREF, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context){
        return sharedPreferences(context).edit();
    }
    public static void writeIntoSharedPref(Context context, String value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(HAS_ENTERED, value);
    }
    public static String readFromPrefFile(Context context){
        return sharedPreferences(context).getString(HAS_ENTERED, null);
    }

    public static void writeNumIntoSharedPref(Context context, int value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(NUMBER_ENTERED, value);
    }

    public static int readNumFromPrefFile(Context context){
        return sharedPreferences(context).getInt(NUMBER_ENTERED, 0);
    }
}
