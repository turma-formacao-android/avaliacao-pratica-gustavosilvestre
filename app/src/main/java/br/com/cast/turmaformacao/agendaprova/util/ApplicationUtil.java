package br.com.cast.turmaformacao.agendaprova.util;

import android.content.Context;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ApplicationUtil {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ApplicationUtil.context = context;
    }

    private ApplicationUtil(){
        super();
    }
}
