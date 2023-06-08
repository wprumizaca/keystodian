package com.keystodian.apikeys.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class ClaveCompuestaAppBuena implements Serializable {

    private String plataforma;

    private String usuario;

    private Long id;


    public ClaveCompuestaAppBuena(String plataforma, String usuario, Long id) {
        this.plataforma = plataforma;
        this.usuario = usuario;
        this.id = id;
    }

    public ClaveCompuestaAppBuena() {
    }


}
