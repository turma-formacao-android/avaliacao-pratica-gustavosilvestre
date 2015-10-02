package br.com.cast.turmaformacao.agendaprova.util;

import android.widget.EditText;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class FormerHelper {

    private FormerHelper(){
        super();
    }

    public static void clearEditTextFields(EditText... fields){
        for(EditText field : fields){
            field.setText("");
        }
    }

    public static boolean verifiedRequiredFields(String requiredMessage,EditText... fields){

        boolean hasRequired = false;

        for(EditText editText : fields){
            if(editText.getText().toString().trim().isEmpty()){
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }

        return hasRequired;
    }
}
