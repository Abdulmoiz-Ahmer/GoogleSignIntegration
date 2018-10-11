package com.example.aceahmer.task_ten;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {

    private String email;
    private String name;
    private Uri ImageUrl;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public static Creator<DataModel> getCREATOR() {
        return CREATOR;
    }

    private String name2;

    protected DataModel(Parcel in) {
        email = in.readString();
        name = in.readString();
        ImageUrl = in.readParcelable(Uri.class.getClassLoader());
        name2=in.readString();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }



    public DataModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeParcelable(ImageUrl, i);
        parcel.writeString(name2);
    }
}
