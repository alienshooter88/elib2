package com.exmple.elib.entity;

import javax.persistence.*;

@Entity
public class Work {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String num;

    @Column(length = 10000)
    private String organization;

    @Column(length = 2000)
    private String works;

    private int work;

    private String compare;

    public Work(){

    }

    public Work(String num, String organization, String works, int work, String compare) {
        this.num = num;
        this.organization = organization;
        this.works = works;
        this.work = work;
        this.compare = compare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }
}
