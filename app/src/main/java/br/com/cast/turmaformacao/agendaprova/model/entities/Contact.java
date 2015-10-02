package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Contact implements Parcelable {

    private Integer id;
    private String name;
    private Address address;
    private List<Telephone> telephonesList;
    private List<SocialNetwork> socialNetworksList;
    private List<Email> emailList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Telephone> getTelephonesList() {
        return telephonesList;
    }

    public void setTelephonesList(List<Telephone> telephonesList) {
        this.telephonesList = telephonesList;
    }

    public List<SocialNetwork> getSocialNetworksList() {
        return socialNetworksList;
    }

    public void setSocialNetworksList(List<SocialNetwork> socialNetworksList) {
        this.socialNetworksList = socialNetworksList;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null)
            return false;
        if (telephonesList != null ? !telephonesList.equals(contact.telephonesList) : contact.telephonesList != null)
            return false;
        return !(socialNetworksList != null ? !socialNetworksList.equals(contact.socialNetworksList) : contact.socialNetworksList != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephonesList != null ? telephonesList.hashCode() : 0);
        result = 31 * result + (socialNetworksList != null ? socialNetworksList.hashCode() : 0);
        return result;
    }



    public Contact() {
        address = new Address();
        telephonesList = new ArrayList<>();
        emailList = new ArrayList<>();
        socialNetworksList = new ArrayList<>();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeParcelable(this.address, 0);
        dest.writeTypedList(telephonesList);
        dest.writeTypedList(socialNetworksList);
        dest.writeTypedList(emailList);
    }

    protected Contact(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.telephonesList = in.createTypedArrayList(Telephone.CREATOR);
        this.socialNetworksList = in.createTypedArrayList(SocialNetwork.CREATOR);
        this.emailList = in.createTypedArrayList(Email.CREATOR);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
