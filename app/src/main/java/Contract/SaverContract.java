package Contract;

import java.util.List;

/**
 * Created by emmanuel on 27/09/2017.
 */
public interface SaverContract{
    interface View{
        void statusSaved(List<String> fileNames, List<String> filePath);
        void statusSaved(boolean saved);
    }
    interface Presenter{
        void getAllStatus();
        void saveStatus(String path);
    }
}
