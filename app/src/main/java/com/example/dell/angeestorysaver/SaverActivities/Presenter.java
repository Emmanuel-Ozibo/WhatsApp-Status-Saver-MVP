package com.example.dell.angeestorysaver.SaverActivities;

import Contract.SaverContract;
import SaverModels.Model;
import SaverModels.MvpDataManager;

import java.io.File;
import java.util.List;

/**
 * Created by emmanuel on 27/09/2017.
 */
public class Presenter implements SaverContract.Presenter, MvpDataManager.OnSaveListener{
    private Model model;
    private SaverContract.View view;

    public Presenter(SaverContract.View view){
        this.model = new Model(this);
        this.view = view;
    }

    @Override
    public void getAllStatus() {
        //called from the view
        model.copyAllStatusIntoFile();
    }

    @Override
    public void saveStatus(String path) {
        //Todo handle status saving here
        model.saveStatus(path);
    }

    @Override
    public void savedSuccessful(boolean saved) {
        view.statusSaved(saved);
    }

    @Override
    public void getAllFileNames(List<String> fileNames, List<String> filePaths){
        view.statusSaved(fileNames, filePaths);
    }
}
