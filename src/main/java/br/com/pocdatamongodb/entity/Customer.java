package br.com.pocdatamongodb.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    @Id
    public String id;

    public String firstName;

    public String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
