package com.ibm.garage.sdpmbe.model;

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
@Table(name = "deploymentslist")
public class deploymentsDM {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_gen")
    @SequenceGenerator(name="contact_gen", sequenceName = "contacts_seq", initialValue = 5, allocationSize=1)
    private Integer id;
    
    @Column(name = "deploymentDate")
    private Date deploymentDate;

	public Date getDeploymentDate()
    {
        return this.deploymentDate;
    }


	public void setDeploymentDate(Date deploymentDate) {
		this.deploymentDate = deploymentDate;
	}

    @Column(name = "deploymentTime")
    private Time deploymentTime;

     public Time getDeploymentTime() {
		return this.deploymentTime;
	}

	public void setDeploymentTime(Time deploymentTime)
    {
        this.deploymentTime = deploymentTime;
    }

    public deploymentsDM()
    {

    }

    public deploymentsDM(Date dd, Time dt)
    {
        this.deploymentDate = dd;
        this.deploymentTime = dt;
    }
    
}
