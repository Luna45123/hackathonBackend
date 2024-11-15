package com.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "dashboard_id")
    private List<Diary> diaries;

    private int membershipCount;
    private int noneCount;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(List<Diary> diaries) {
        this.diaries = diaries;
    }

    public int getMembershipCount() {
        return membershipCount;
    }

    public void setMembershipCount(int membershipCount) {
        this.membershipCount = membershipCount;
    }

    public int getNoneCount() {
        return noneCount;
    }

    public void setNoneCount(int noneCount) {
        this.noneCount = noneCount;
    }
}
