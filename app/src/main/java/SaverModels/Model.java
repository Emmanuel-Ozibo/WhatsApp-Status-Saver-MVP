package SaverModels;

import Helper.SdCardHelper;
import Helper.SharedPref;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by emmanuel on 27/09/2017.
 */


public class Model implements MvpDataManager{
    private OnSaveListener saveListener;

    public Model(OnSaveListener saveListener) {
        this.saveListener = saveListener;
    }

    @Override
    public void copyAllStatusIntoFile(){
        //check if sd card is present
        boolean sdCardPresent = SdCardHelper.isSdCardPresent();
        if (sdCardPresent){
            //save the selected image
            File file = Environment.getExternalStorageDirectory();
            String whatsAppPath = file.getAbsolutePath() + "/WhatsApp/Media/.Statuses/";
            File statusFile = new File(whatsAppPath);
            if (statusFile.isDirectory()){
                File[] files = statusFile.listFiles();

                List<String>fileNames = new ArrayList<>();
                List<String>filePaths = new ArrayList<>();
                for (int i = 0; i < files.length; i++){
                    fileNames.add(files[i].getName());
                    filePaths.add(files[i].getAbsolutePath());
                }
                saveListener.getAllFileNames(fileNames, filePaths);

            }
        }
    }

    @Override
    public void saveStatus(String path) {
        File Externalpath1 = Environment.getExternalStorageDirectory();
        String desFileName = Externalpath1.getAbsolutePath() + "/AngeeStorySaver/";
        File newDir = new File(desFileName);
        if (!newDir.isDirectory() && !newDir.exists()){
            boolean directoryCreated = newDir.mkdir();
            if (directoryCreated){
                createNewFile(desFileName, path);
            }
        }else {
            createNewFile(desFileName, path);
        }
    }

    private void createNewFile(String desFileName, String path){
        //create a new file
        String savedStoryPath = desFileName + getName(path);
        File SaveStoryFile = new File(savedStoryPath);
        if (!SaveStoryFile.isFile() && !SaveStoryFile.exists()){
            Log.i("File Status", "Doesnt exist");
            try {
                boolean fileCreated = SaveStoryFile.createNewFile();
                if (fileCreated){
                    Log.i("File Status", "File Created");
                    copyStatusIntoFile(savedStoryPath, path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            saveListener.savedSuccessful(false);
            Log.i("File Status", "File already exists");
        }
    }

    /**
     * @param savedStoryPath This represents the new path we want to make
     * @param path This is where it is coming from
     * */
    private void copyStatusIntoFile(String savedStoryPath, String path) {
        try {
            FileChannel inComingChannel = new FileInputStream(new File(path)).getChannel();
            FileChannel destinationChannel = new FileOutputStream(new File(savedStoryPath)).getChannel();

            long id = destinationChannel.transferFrom(inComingChannel, 0, inComingChannel.size());
            if (id > 0){
                Log.i("Copy Status: ", "Copied");
                saveListener.savedSuccessful(true);
                //give a notification that it has been done
            }else {
                Log.i("Copy Status: ", "Cant copy");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName(String path){
        return path.substring(45, path.length());
    }

}
