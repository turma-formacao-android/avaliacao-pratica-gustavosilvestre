package br.com.cast.turmaformacao.agendaprova.model.service;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.Email;
import br.com.cast.turmaformacao.agendaprova.model.persistence.email.EmailRepository;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class EmailBusinessService {

    public EmailBusinessService(){
        super();
    }

    public static void save(Email email){
        EmailRepository.save(email);
    }

    public static List<Email> findAll(){
        return EmailRepository.findAll();
    }

    public static List<Email> getByContactId(Contact contact){
        return EmailRepository.getEmailByContactId(contact.getId());
    }

    public static void delete(Email email) {
        EmailRepository.delete(email.getId());
    }
}
