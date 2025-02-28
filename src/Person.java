// Represents a person with their name, address information, and age
class Person {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private int age;
    private String fullAddress;

    public Person(String firstName, String lastName, String streetAddress,
                  String city, String state, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.age = age;
        this.fullAddress = streetAddress + ", " + city + ", " + state;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() { return streetAddress; }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getAge() {
        return age;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + fullAddress + " " + age;
    }
}