package br.com.cast.turmaformacao.agendaprova.model.service;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.Email;
import br.com.cast.turmaformacao.agendaprova.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agendaprova.model.entities.Telephone;
import br.com.cast.turmaformacao.agendaprova.model.persistence.contact.ContactRepository;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class ContactBusinessService {

    public ContactBusinessService() {
        super();
    }

    public static void save(Contact contact) {
        ContactRepository.save(contact);

        for (Telephone telephone : contact.getTelephonesList()) {
            telephone.setContact(contact);
            TelephoneBusinessService.save(telephone);
        }

        for(Email email : contact.getEmailList()){
            email.setContact(contact);
            EmailBusinessService.save(email);
        }

        for(SocialNetwork socialNetwork : contact.getSocialNetworksList()){
            socialNetwork.setContact(contact);
            SocialNetworkBusinessService.save(socialNetwork);
        }

    }

    public static List<Contact> findAllWithoutDependecies(){
        return ContactRepository.findAll();
    }


    public static List<Contact> findAll() {

        List<Contact> contactList = ContactRepository.findAll();

        for (Contact contact : contactList) {
            contact.setSocialNetworksList(getSocialNetworkDependecies(contact));
            contact.setEmailList(getEmailDependecies(contact));
            contact.setTelephonesList(getTelephoneDependecies(contact));
        }

        return contactList;

    }

    public static List<Contact> getByName(String name) {

        List<Contact> contactList = ContactRepository.getByName(name);

        return contactList;
    }

    private static List<Email> getEmailDependecies(Contact contact) {

        List<Email> emailList = EmailBusinessService.getByContactId(contact);

        for (Email email : emailList) {
            email.setContact(contact);
        }

        return emailList;

    }

    private static List<Telephone> getTelephoneDependecies(Contact contact) {

        List<Telephone> telephoneList = TelephoneBusinessService.getByContactId(contact);

        for(Telephone telephone : telephoneList){
            telephone.setContact(contact);
        }

        return telephoneList;
    }

    public static Contact getDependencies(Contact contact){

        contact.setEmailList(getEmailDependecies(contact));
        contact.setSocialNetworksList(getSocialNetworkDependecies(contact));
        contact.setTelephonesList(getTelephoneDependecies(contact));

        return contact;
    }

    private static List<SocialNetwork> getSocialNetworkDependecies(Contact contact) {
        List<SocialNetwork> socialNetworkList = SocialNetworkBusinessService.getByContactId(contact);

        for(SocialNetwork socialNetwork : socialNetworkList){
            socialNetwork.setContact(contact);
        }
        return socialNetworkList;
    }


    public static void delete(Contact contact) {

        deleteAlldependencies(contact);
        ContactRepository.delete(contact.getId());


    }

    private static void deleteAlldependencies(Contact contact) {
        for (Email email : contact.getEmailList()) {
            EmailBusinessService.delete(email);
        }

        for (Telephone telephone : contact.getTelephonesList()) {
            TelephoneBusinessService.delete(telephone);
        }

        for (SocialNetwork socialNetwork : contact.getSocialNetworksList()) {
            SocialNetworkBusinessService.delete(socialNetwork);
        }
    }

    public static Contact getById(int id) {
        Contact contact = ContactRepository.getById(id);

        contact = getDependencies(contact);

        return contact;
    }

    public static void saveContactAndroid(List<Contact> lista) {

        for(Contact contact : lista){
            save(contact);
        }
    }
}
