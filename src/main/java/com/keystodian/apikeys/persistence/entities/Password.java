package com.keystodian.apikeys.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter
@Entity
@Table(name="passwords")
public class Password {

    @Id
    private String user_id;

    private String app;

    private String password;


    private LocalDateTime creation_date;

    @PrePersist
    private void setCreationDate(){
        this.creation_date = LocalDateTime.now();

    }

    @ManyToOne
    @JoinColumn(name="id", nullable = false)//nullable false
    private User user;


}
