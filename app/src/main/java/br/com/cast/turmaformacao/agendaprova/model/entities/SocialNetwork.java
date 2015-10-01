package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class SocialNetwork implements Parcelable {

    private Integer id;
    private String name;
    private Contact contact;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.contact, flags);
    }

    public SocialNetwork() {
    }

    protected SocialNetwork(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<SocialNetwork> CREATOR = new Parcelable.Creator<SocialNetwork>() {
        public SocialNetwork createFromParcel(Parcel source) {
            return new SocialNetwork(source);
        }

        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    };
}
