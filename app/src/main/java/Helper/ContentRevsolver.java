package Helper;

import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by emmanuel on 28/09/2017.
 */
public class ContentRevsolver {
    public static ContentResolver getContentResolver(Context context){
        return context.getContentResolver();
    }
}
