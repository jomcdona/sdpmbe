package com.ibm.garage.sdpmbe.recovery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;

import java.sql.Date;
import java.sql.Time;



@Entity // This tells Hibernate to make a table out of this class
@Table(name = "recovertimeslist")
public class recoveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rectimes_gen")
    @SequenceGenerator(name="rectimes_gen", sequenceName = "rectimes_seq", initialValue = 1, allocationSize=1)
    private Integer id;


	public Integer getId() {
		return this.id;
    }

     public void setId(Integer id) {
		this.id = id;
	};

    @Column(name = "recstartdate")
    private Date recstartdate;

    public Date getRecstartdate() {
        return this.recstartdate;
    }

    public void setRecstartdate(Date recstartdate) {
        this.recstartdate = recstartdate;
    }


    @Column(name = "recstarttime")
    private Time recstarttime;

    public Time getRecstarttime() {
        return this.recstarttime;
    }

    public void setRecstarttime(Time recstarttime) {
        this.recstarttime = recstarttime;
    }

    @Column(name = "recduration")
    private int recduration;

    public int getRecduration() {
        return this.recduration;
    }

    public void setRecduration(int recduration) {
        this.recduration = recduration;
    }


    public recoveryEntity(Date recstartdate, Time recstarttime, int recduration) {
        this.recstartdate = recstartdate;
        this.recstarttime = recstarttime;
        this.recduration = recduration;
    }

    public recoveryEntity()
    {

    }
    
}