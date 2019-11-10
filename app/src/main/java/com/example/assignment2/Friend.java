package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {

    private String username;
    private String email;
    private int phoneNum;

    public Friend(String username, String email, int phoneNum) {
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Friend() {
    }


    protected Friend(Parcel in) {
        username = in.readString();
        email = in.readString();
        phoneNum = in.readInt();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return
                "Username: " + username + '\n' +
                "Email: " + email + '\n' +
                "Phone Number: " + phoneNum + '\n';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeInt(phoneNum);
    }
}
