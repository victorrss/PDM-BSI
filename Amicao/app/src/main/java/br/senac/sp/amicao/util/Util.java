package br.senac.sp.amicao.util;

import android.app.AlertDialog;
import android.content.Context;

public final class Util {
    public static String URL_API = "https://oficinacordova.azurewebsites.net/";

    public static void showDialog(String val, String title, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
