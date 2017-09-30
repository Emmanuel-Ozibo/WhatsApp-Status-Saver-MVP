package com.example.dell.angeestorysaver.SaverActivities;

import Contract.SaverContract;
import Helper.SharedPref;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.dell.angeestorysaver.R;
import com.example.dell.angeestorysaver.SaverActivities.Fragments.StatusListFragment;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SaverContract.View, StatusListFragment.OnFragmentAttachedListener{
    private StatusListFragment listFragment;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);
        presenter.getAllStatus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String value = SharedPref.readFromPrefFile(getApplicationContext());
        if (value != null){
            //todo Handle exit Activity
        }
    }

    @Override
    public void statusSaved(List<String> fileNames, List<String> filePaths) {

        if (listFragment == null){
            listFragment = StatusListFragment.newInstance(fileNames, filePaths);
             FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
             ft.add(R.id.fragment_container, listFragment);
             ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
             ft.commit();
        }
    }

    @Override
    public void statusSaved(boolean saved) {
        if (saved){
            Toast.makeText(this, "Status Saved", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "File Already Exists, Status Saved", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void downLoadClicked(String path) {
        presenter.saveStatus(path);
    }

    @Override
    protected void onPause() {
        super.onPause();
        listFragment = null;
        reScanSdCard();

    }

    private void reScanSdCard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Intent mediaScannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+
                    Environment.getExternalStorageDirectory()));
//            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AngeeStorySaver/"));
//            mediaScannerIntent.setData(uri);
            sendBroadcast(mediaScannerIntent);
        }else {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+
                    Environment.getExternalStorageDirectory())));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllStatus();
    }
}
