package university.entities;

import java.util.Objects;

public class Person {

    private int id;
    private String fullName;
    private String email;

    public Person(int id, String fullName, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID має бути більше 0.");
        }

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("ПІБ не може бути порожнім.");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Невірний формат email.");
        }

        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("ПІБ не може бути порожнім.");
        }

        this.fullName = fullName;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Невірний формат email.");
        }

        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + fullName +
                ", Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Person)) {
            return false;
        }

        Person person = (Person) obj;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}