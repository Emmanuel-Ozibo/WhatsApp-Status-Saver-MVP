package Helper;

import android.content.Context;
import android.os.Environment;

/**
 * Created by emmanuel on 27/09/2017.
 */
public class SdCardHelper{
    public static boolean isSdCardPresent(){

        String state = Environment.getExternalStorageState();
        switch (state){
            case Environment.MEDIA_MOUNTED:
                return true;
            case Environment.MEDIA_BAD_REMOVAL:
                return false;
            case Environment.MEDIA_REMOVED:
                return false;
        }
        return true;
    }
}
