package com.vitalinvent.tasks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple JavaBean domain object that represents Customer.
 *
 *
 *
 */

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
public class Task extends BaseEntity {

    @Column(name = "status")
    private String status;

    @Column(name = "timestamp_")
    private String timestamp;

    public String getStatus(){
        return this.status;
    }
    public void setStatus(Status status_){
        switch (status_){
            case created:
                this.status=status_.toString();
                break;
            case running:
                this.status=status_.toString();
                break;
            case finished:
                this.status=status_.toString();
                break;
        }
    }

    public String getTimestamp(){
        return this.timestamp;
    }
    public static String dateTimeISO8601() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return OffsetDateTime.now().format(dtf);
    }
    public void setTimestamp(){
        this.timestamp=dateTimeISO8601();
    }
}
