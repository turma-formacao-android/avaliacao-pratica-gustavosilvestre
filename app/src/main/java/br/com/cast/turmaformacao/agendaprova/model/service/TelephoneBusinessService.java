package br.com.cast.turmaformacao.agendaprova.model.service;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.Telephone;
import br.com.cast.turmaformacao.agendaprova.model.persistence.telephone.TelephoneRepostitory;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class TelephoneBusinessService {

    private TelephoneBusinessService() {
        super();
    }

    public static void save(Telephone telephone) {

       TelephoneRepostitory.save(telephone);

    }

    public static List<Telephone> getByContactId(Contact contact) {

       return TelephoneRepostitory.getTelephoneByContactId(contact.getId());

    }

    public static List<Telephone> findAll(){
        return TelephoneRepostitory.findAll();
    }

    public static void delete(Telephone telephone) {

        if(telephone.getId() == null)
            return;

        TelephoneRepostitory.delete(telephone.getId());
    }
}
