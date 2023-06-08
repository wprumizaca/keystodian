package com.keystodian.apikeys.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter @Setter
@Entity
@IdClass(ClaveCompuestaAppBuena.class)
@Table(name="apps")
public class AppBuena {

    @Id
    private String plataforma;

    @Id
    private String usuario;


    private String password;


//    private LocalDateTime creation_date;
//
//    @PrePersist
//    private void setCreationDate(){
//        this.creation_date = LocalDateTime.now();
//
//    }

    @ManyToOne
    @JoinColumn(name="id", nullable = false)//nullable false
    private User user;


}
