package fr.corbiko.wemouv.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.GeoPoint;

import java.lang.ref.Reference;
import java.util.Objects;

public class Establishment implements Parcelable {

    private  String name;
    private  String content;
    private  GeoPoint coord;
    private  String image1;
    private  String image2;
    private  String image3;
    private  String phone = null;
    private  Reference address = null;

    public Establishment() {
        // needed no-arg constructor for Firestore
    }

    public Establishment(String name, String content, GeoPoint coord, String image1, String image2, String image3){
        this.name = Objects.requireNonNull(name);
        this.content = Objects.requireNonNull(content);
        if ((int) coord.getLongitude() > 180 || (int) coord.getLongitude() < -180 || (int) coord.getLatitude() > 90 || (int) coord.getLatitude() < -90){
            throw new IllegalArgumentException("Error, longitude must be between -180 and 180 and latitude must be between -90 and 90");
        }
        this.coord = new GeoPoint(coord.getLatitude(), coord.getLongitude());
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public String getContent() { return content; }

    public String getName() { return name; }

    public GeoPoint getCoord() {
        return coord;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getPhone() {
        return phone;
    }

    public Reference getAddress() {
        return address;
    }

    /* Parcelable -> Créer Establishment à envoyer vers une autre Activity */
    public Establishment(Parcel in){
        name = in.readString();
        content = in.readString();
        double[] coord_point = new double[2];
        in.readDoubleArray(coord_point);
        coord = new GeoPoint(coord_point[0], coord_point[1]);
        image1 = in.readString();
        image2 = in.readString();
        image3 = in.readString();
    }

    /* Parcelable -> Init de l'Establishment à envoyer vers une autre Activity */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(content);
        dest.writeDoubleArray(new double[]{coord.getLongitude(), coord.getLatitude()});
        dest.writeString(image1);
        dest.writeString(image2);
        dest.writeString(image3);
    }

    /* Parcelable -> Ouverture de l'Establishment à envoyer vers une autre Activity */
    public static final Parcelable.Creator<Establishment> CREATOR = new Parcelable.Creator<Establishment>(){
        public Establishment createFromParcel(Parcel in) {
            return new Establishment(in);
        }

        public Establishment[] newArray(int size) {
            return new Establishment[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }
}
