package Helper;

/**
 * Created by emmanuel on 28/09/2017.
 */
public class SimpleCalculations {

    public static int getNumOfColumn(int ximageWidth, int xphoneWidth){
        return Math.round(xphoneWidth/ximageWidth);
    }
}
