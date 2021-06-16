package com.ibm.garage.sdpmbe.leadtimes.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "leadtime")
public class leadtimeEntity {

    @Id
    @Column(name = "leadtime")
    private int leadtime;

    public leadtimeEntity()
    {

    }

    public leadtimeEntity(int lt)
    {
        this.leadtime = lt;
    }

    public int getLeadtime() {
        return this.leadtime;
    }

    public void setLeadtime(int leadtime) {
        this.leadtime = leadtime;
    };
    
}
