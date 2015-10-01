package br.com.cast.turmaformacao.agendaprova.controllers.aSyncTask;

import br.com.cast.turmaformacao.agendaprova.model.entities.Address;

/**
 * Created by Administrador on 01/10/2015.
 */
public interface AddressInterfaceTask {

    void afterSearchZipCode(Address address);
}
