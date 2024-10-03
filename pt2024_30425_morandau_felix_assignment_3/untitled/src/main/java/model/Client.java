package model;

import java.util.Objects;

/**
 * Represents a client entity.
 */
public class Client {
    private int id;
    private String first_name;
    private String last_name;
    private String phone_nr;
    private String email;

    /**
     * Constructs a client with specified attributes.
     * @param firstName The first name of the client.
     * @param lastName The last name of the client.
     * @param phoneNumber The phone number of the client.
     * @param email The email address of the client.
     */
    public Client(String firstName, String lastName, String phoneNumber, String email) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone_nr = phoneNumber;
        this.email = email;
    }

    /**
     * Constructs an empty client object.
     */
    public Client(){}

    /**
     * Returns a string representation of the client.
     * @return The string representation of the client.
     */
    public String toString(){
        return this.id + ". " +  this.first_name + " " + last_name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(first_name, client.first_name) &&
                Objects.equals(last_name, client.last_name) &&
                Objects.equals(phone_nr, client.phone_nr) &&
                Objects.equals(email, client.email);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_nr() {
        return phone_nr;
    }

    public void setPhone_nr(String phone_nr) {
        this.phone_nr = phone_nr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
