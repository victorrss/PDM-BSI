package br.senac.sp.basico.util;

import android.app.AlertDialog;
import android.content.Context;

public class Util {

    public static void showDialog(String message, String title, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static Double toDouble(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (Exception e) {
            return null;
        }
    }
}
