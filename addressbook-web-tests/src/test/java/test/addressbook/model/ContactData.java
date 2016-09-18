package test.addressbook.model;

public class ContactData {
    private int id;
    private final String name;
    private final String lastname;
    private final String address;
    private final String phone;
    private final String email;
    private String group;


    public ContactData(int id, String name, String lastname, String address, String phone, String email, String group) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;

        this.phone = phone;
        this.email = email;

        this.group = group;
    }

    public ContactData(String name, String lastname, String address, String phone, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastname = lastname;
        this.address = address;

        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;


    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
