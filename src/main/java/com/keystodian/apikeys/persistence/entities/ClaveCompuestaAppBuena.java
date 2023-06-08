package com.keystodian.apikeys.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Getter @Setter
public class ClaveCompuestaAppBuena implements Serializable {

    private String plataforma;

    private String usuario;

    public ClaveCompuestaAppBuena(String plataforma, String usuario) {
        this.plataforma = plataforma;
        this.usuario = usuario;
    }

    public ClaveCompuestaAppBuena() {
    }
}
