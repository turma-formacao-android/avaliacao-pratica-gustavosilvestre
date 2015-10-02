package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Email implements Parcelable {

    private Integer id;
    private String email;
    private Contact contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.email == null ? "" : this.email);
        dest.writeParcelable(this.contact, flags);
    }

    public Email() {
        contact = new Contact();
    }

    protected Email(Parcel in) {
        Integer id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = id == -1 ? null : id;
        this.email = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
