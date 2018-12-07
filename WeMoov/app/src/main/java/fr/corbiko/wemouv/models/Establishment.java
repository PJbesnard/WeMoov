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
    private  int image1;
    private  int image2;
    private  int image3;
    private  String phone = null;
    private  Reference address = null;

    public Establishment() {
        // needed no-arg constructor for Firestore
    }

    public Establishment(String name, String content, GeoPoint coord, int image1, int image2, int image3){
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

    public int getImage1() {
        return image1;
    }

    public int getImage2() {
        return image2;
    }

    public int getImage3() {
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
        image1 = in.readInt();
        image2 = in.readInt();
        image3 = in.readInt();
    }

    /* Parcelable -> Init de l'Establishment à envoyer vers une autre Activity */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(content);
        dest.writeDoubleArray(new double[]{coord.getLongitude(), coord.getLatitude()});
        dest.writeInt(image1);
        dest.writeInt(image2);
        dest.writeInt(image3);
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
