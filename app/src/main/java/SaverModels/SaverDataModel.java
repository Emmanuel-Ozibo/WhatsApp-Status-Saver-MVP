package SaverModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 27/09/2017.
 */
public class SaverDataModel implements Parcelable{
    private String name;


    protected SaverDataModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<SaverDataModel> CREATOR = new Creator<SaverDataModel>() {
        @Override
        public SaverDataModel createFromParcel(Parcel in) {
            return new SaverDataModel(in);
        }

        @Override
        public SaverDataModel[] newArray(int size) {
            return new SaverDataModel[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
