package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Address implements Parcelable{

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("logradouro")
    private String street;

    @JsonIgnore
    private String number;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

    public Address() {
        super();
    }

    public Address(Parcel in) {
        super();
        readToParcel(in);
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(zipCode == null ? "" : zipCode);
        dest.writeString(street == null ? "" : street);
        dest.writeString(neighborhood == null ? "" : neighborhood);
        dest.writeString(city == null ? "" : city);
        dest.writeString(state == null ? "" : state);
        dest.writeString(number == null ? "" : number);
        dest.writeString(complement == null ? "" : complement);
    }

    public void readToParcel(Parcel in) {
        zipCode = in.readString();
        street = in.readString();
        neighborhood = in.readString();
        city = in.readString();
        state = in.readString();
        number = in.readString();
        complement = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
