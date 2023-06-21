package server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence",
    sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "customer_id_sequence")
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    @Transient
    private Integer age;
    private Integer balance;

    public Customer(long id, String firstName, String lastName, LocalDate dob, Integer balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.balance = balance;
    }

    public Customer() {

    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
