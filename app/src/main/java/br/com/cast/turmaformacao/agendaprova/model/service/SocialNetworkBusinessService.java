package br.com.cast.turmaformacao.agendaprova.model.service;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agendaprova.model.persistence.socialNetwork.SocialNetworkRepository;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class SocialNetworkBusinessService {

    private SocialNetworkBusinessService(){
        super();
    }

    public static void save(SocialNetwork socialNetwork){
        SocialNetworkRepository.save(socialNetwork);
    }

    public static List<SocialNetwork> findAll(){
        return SocialNetworkRepository.findAll();
    }

    public static List<SocialNetwork> getByContactId(Contact contact){

        return SocialNetworkRepository.getSocialNetworkByContactId(contact.getId());
    }

    public static void delete(SocialNetwork socialNetwork) {
        SocialNetworkRepository.delete(socialNetwork.getId());
    }
}
