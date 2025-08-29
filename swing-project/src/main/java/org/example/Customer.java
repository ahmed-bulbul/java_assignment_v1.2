package org.example;

public class Customer {
    private int customerId;
    private String shortName, fullName, address1, address2, address3, city, postalCode;

    public Customer(int customerId, String shortName, String fullName,
                    String address1, String address2, String address3,
                    String city, String postalCode) {
        this.customerId = customerId;
        this.shortName = shortName;
        this.fullName = fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters and setters...
    public int getCustomerId() { return customerId; }
    public String getShortName() { return shortName; }
    public String getFullName() { return fullName; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getAddress3() { return address3; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
}
