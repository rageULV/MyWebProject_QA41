package model;

import java.io.*;
import java.util.Objects;

public class Contact implements Serializable {

    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String address;
    private String description;

    public Contact() {
    }
    public Contact(String name, String lastname, String phone, String email, String address, String description) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return  Objects.equals(getName(), contact.getName()) &&
                 Objects.equals(getLastname(), contact.getLastname()) &&
                  Objects.equals(getPhone(), contact.getPhone()) &&
                   Objects.equals(getEmail(), contact.getEmail()) &&
                    Objects.equals(getAddress(), contact.getAddress()) &&
                     Objects.equals(getDescription(), contact.getDescription());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getLastname(),
                getPhone(),
                getEmail(),
                getAddress(),
                getDescription());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    public static void serializeContact(Contact contact, String fileName) throws IOException {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(contact);
    }
    public static Contact deserializeContact(String fileName) {
        try (
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));){
            return (Contact) inputStream.readObject();
        } catch (IOException |ClassNotFoundException e) {
            System.out.println("Error during contact deserialization");
            throw new RuntimeException(e);
        }
    }
}
