package com.image_app.cache;

import java.io.File;
import android.content.Context;
import android.util.Log;

public class FileCache {
    
    private File cacheDir;
    final String SDCARD_APP_FOLDER_NAME="ImageTestRuh";

    public FileCache(Context context){
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),SDCARD_APP_FOLDER_NAME);
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
        {
          Boolean res=cacheDir.mkdirs();
          if(!res)
              Log.e("ERR>>","folder not created");
        }
    }
    
    public File getFile(String url){
        String filename=String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
        
    }
    
    public void clear(){
        File[] files=cacheDir.listFiles();
        for(File f:files)
            f.delete();
    }

}