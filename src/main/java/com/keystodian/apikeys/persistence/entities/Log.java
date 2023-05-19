package com.keystodian.apikeys.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "logs")
public class Log implements Serializable {

    @Id
    private String user_id;

    @Id
    private LocalDateTime log_time;

    @PrePersist
    private void setCreationDate(){
        this.log_time = LocalDateTime.now();

    }

    private String logged_ip;

    @ManyToOne
    @JoinColumn(name="id", nullable = false)//nullable false
    private User user;


}
