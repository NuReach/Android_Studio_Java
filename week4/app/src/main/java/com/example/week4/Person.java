package com.example.week4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Person implements Parcelable  {
    private String firstName;
    private String lastName;
    private String email;
    private String state;
    private String phone;
    private String gender;

    public Person(String firstName, String lastName, String email, String state, String phone, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.state = state;
        this.phone = phone;
        this.gender = gender;
    }

    public Person () {}

    public Person(String firstName, String lastName, String state, String phone, String gender) {
    }

    @Override
    public String toString() {
        return
                "First name  " + firstName + "\n" +
                        "Last name  " + lastName + "\n" +
                        "Email   " + email + "\n" +
                        "Phone   " + phone + "\n" +
                        "State   " + state + "\n" +
                        "Gender   " + gender + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email) && Objects.equals(state, person.state) && Objects.equals(phone, person.phone) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, state, phone, gender);
    }

    protected Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        state = in.readString();
        phone = in.readString();
        gender = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(state);
        dest.writeString(phone);
        dest.writeString(gender);
    }
}
