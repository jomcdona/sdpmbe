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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deployments_gen")
    @SequenceGenerator(name="deployments_gen", sequenceName = "deployments_seq", initialValue = 1, allocationSize=1)
    private Integer id;

	public Integer getId() {
		return this.id;
    }

     public void setId(Integer id) {
		this.id = id;
	};

    @Column(name = "deploymentdate")
    private Date deploymentdate;

	public Date getDeploymentDate()
    {
        return this.deploymentdate;
    }


	public void setDeploymentDate(Date deploymentdate) {
		this.deploymentdate = deploymentdate;
	}

    @Column(name = "deploymenttime")
    private Time deploymenttime;

     public Time getDeploymentTime() {
		return this.deploymenttime;
	}

	public void setDeploymentTime(Time deploymenttime)
    {
        this.deploymenttime = deploymenttime;
    }

    public deploymentsDM()
    {

    }

    public deploymentsDM(Date dd, Time dt)
    {
        this.deploymentdate = dd;
        this.deploymenttime = dt;
    }
    
}
