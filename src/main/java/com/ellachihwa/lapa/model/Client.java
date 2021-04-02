package com.ellachihwa.lapa.model;

import com.ellachihwa.lapa.utils.CustomDateDeserializer;
import com.ellachihwa.lapa.utils.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({ "policyCoverageList" })
@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //attributes
    private Long id;
    private String firstName;
    private String lastName;
    private String title;
    private String phoneNumber;
    private String email;
    private String idNumber;


    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date dateOfBirth;
    @Enumerated
    private Gender gender;




    @OneToOne
    private User user;


    @JsonManagedReference(value = "claim-client")
    @OneToMany(mappedBy = "client")
    private List<InsuranceClaim> claims = new ArrayList<>();


    @JsonManagedReference(value="payment-client")
    @OneToMany(mappedBy = "client")
    private List<Payment> payments = new ArrayList<>();


    @JsonBackReference(value="coverage")
    @OneToMany(mappedBy = "client")
    List<PolicyCoverage> policyCoverageList;

    @Column(columnDefinition = "boolean default false")
    private boolean newEntry ;

    @Transient
    public String getName(){
        return firstName + ' ' + lastName;
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNewEntry() {
        return newEntry;
    }

    public void setNewEntry(boolean newEntry) {
        this.newEntry = newEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    public Date getDateOfBirth() {

        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<InsuranceClaim> getClaims() {
        return claims;
    }

    public void setClaims(List<InsuranceClaim> claims) {
        this.claims = claims;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<PolicyCoverage> getPolicyCoverageList() {
        return policyCoverageList;
    }

    public void setPolicyCoverageList(List<PolicyCoverage> policyCoverageSet) {
        this.policyCoverageList = policyCoverageSet;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", claims=" + claims +
                ", payments=" + payments +
                ", policyCoverageList=" + policyCoverageList +
                '}';
    }
}
