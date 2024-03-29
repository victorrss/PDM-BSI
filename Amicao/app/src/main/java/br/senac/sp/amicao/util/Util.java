package br.senac.sp.amicao.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.text.NumberFormat;
import java.util.Locale;

import br.senac.sp.amicao.model.Customer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Util {
    public static String URL_API = "https://oficinacordova.azurewebsites.net/";
    public static String searchTerm = null;
    public static Integer categoryId = null;
    public static Customer customerLoggedIn = null;

    public static Integer toInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String formatBRLValue(Double d){
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(d);
    }

    public static void showDialog(String message, String title, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showToast(String message, Context context) {
        Toast toast= Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

    public static ImageLoaderConfiguration getImageLoaderConfig(Context context) {
        return new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Util.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SharedPreferences getPreference(Context context){
        SharedPreferences pref = context.getSharedPreferences("settingsDefault", context.MODE_PRIVATE);
        return pref;
    }

}
