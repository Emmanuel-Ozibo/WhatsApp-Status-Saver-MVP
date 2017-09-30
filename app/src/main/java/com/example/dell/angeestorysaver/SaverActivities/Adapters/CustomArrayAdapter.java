package com.example.dell.angeestorysaver.SaverActivities.Adapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dell.angeestorysaver.R;
import com.example.dell.angeestorysaver.SaverActivities.Fragments.StatusListFragment;

import java.io.File;
import java.util.List;


/**
 * Created by emmanuel on 28/09/2017.
 */


public class CustomArrayAdapter extends ArrayAdapter<String>{

    private List<String>modelList;
    private Context context;
    private int resource;
    private List<String>filePaths;
    private AdapterFragmentInteraction interaction;

    public CustomArrayAdapter(Context context, int resource, List<String> objects,
                              List<String> filePath, AdapterFragmentInteraction interaction){
        super(context, resource, objects);
        this.modelList = objects;
        this.context = context;
        this.interaction=interaction;
        this.resource = resource;
        this.filePaths = filePath;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }
        TextView fileNameTv = convertView.findViewById(R.id.fileName);
        ImageView imageThumbNail = convertView.findViewById(R.id.image_view);
        ImageView download = convertView.findViewById(R.id.downLoadBtn);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interaction.downLoadClicked(filePaths.get(position));
            }
        });

        fileNameTv.setText(modelList.get(position));

        String filePath = filePaths.get(position);

        createDisplayImages(filePath, imageThumbNail);

        return convertView;
    }

    private void createDisplayImages(String filePath, final ImageView imageThumbNail) {

        //do the image processing off the main thread using async task
        AsyncTask<String, Void, Bitmap> asyncTask = new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                String pathToFile = strings[0];
                Bitmap bitmap = null;
                if (pathToFile.endsWith(".mp4")){
                    //video file
                    bitmap = ThumbnailUtils.createVideoThumbnail(pathToFile, MediaStore.Images.Thumbnails.MINI_KIND);
                }else {
                    bitmap = BitmapFactory.decodeFile(pathToFile);
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                setImageViewThumbnail(imageThumbNail, bitmap);
            }
        };
        asyncTask.execute(filePath);

    }

    private void setImageViewThumbnail(ImageView imageThumbNail, Bitmap bitmap) {
        imageThumbNail.setImageBitmap(bitmap);
    }

    @Override
    public int getCount() {
        if (modelList == null){
            return 0;
        }
        return modelList.size();

    }

    public interface AdapterFragmentInteraction{
        void downLoadClicked(String path);
    }
}
