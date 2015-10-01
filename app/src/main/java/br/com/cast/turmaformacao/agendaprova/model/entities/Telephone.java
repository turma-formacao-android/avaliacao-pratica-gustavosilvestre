package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Telephone implements Parcelable {

    private Integer id;
    private String telephone;
    private Contact contact;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.telephone);
        dest.writeParcelable(this.contact, flags);
    }

    public Telephone() {
    }

    protected Telephone(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.telephone = in.readString();
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
