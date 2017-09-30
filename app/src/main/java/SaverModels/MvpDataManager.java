package SaverModels;

import java.io.File;
import java.util.List;

/**
 * Created by emmanuel on 27/09/2017.
 */
public interface MvpDataManager{

    interface OnSaveListener{
        void savedSuccessful(boolean saved);
        void getAllFileNames(List<String> fileNames, List<String> filePath);
    }
    void copyAllStatusIntoFile();
    void saveStatus(String path);

}
