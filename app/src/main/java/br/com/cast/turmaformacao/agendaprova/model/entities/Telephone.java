package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Telephone implements Parcelable {

    private Integer id;
    private String type;
    private String number;
    private Contact contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return this.type+" - "+this.number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.number == null ? "" : this.number);
        dest.writeString(this.type == null ? "" : this.type);
        dest.writeParcelable(this.contact,flags);
    }

    public Telephone() {
        contact = new Contact();
    }

    protected Telephone(Parcel in) {
        Integer id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = id == -1 ? null : id;
        this.number = in.readString();
        this.type = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Telephone> CREATOR = new Parcelable.Creator<Telephone>() {
        public Telephone createFromParcel(Parcel source) {
            return new Telephone(source);
        }

        public Telephone[] newArray(int size) {
            return new Telephone[size];
        }
    };
}
