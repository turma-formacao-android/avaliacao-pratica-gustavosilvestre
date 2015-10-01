package br.com.cast.turmaformacao.agendaprova.ApplicationManager;

import android.app.Application;

import br.com.cast.turmaformacao.agendaprova.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ApplicationManager extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
