package Helper;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by emmanuel on 28/09/2017.
 */
public class DpToPx {

    public static int PxValue(Context context, float dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
