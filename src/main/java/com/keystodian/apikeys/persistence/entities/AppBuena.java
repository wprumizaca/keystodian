package com.keystodian.apikeys.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter @Setter
@Entity
@Table(name="apps")
public class AppBuena {


    @EmbeddedId
    private ClaveCompuestaAppBuena claveprimaria;


    private String password;


//    private LocalDateTime creation_date;
//
//    @PrePersist
//    private void setCreationDate(){
//        this.creation_date = LocalDateTime.now();
//
//    }



    @ManyToOne
    @JoinColumn(name="id", nullable = false, insertable = false, updatable = false)//nullable false
    private User user;

}
