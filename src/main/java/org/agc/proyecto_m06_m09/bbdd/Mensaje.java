package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "MENSAJES")
public class Mensaje {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER_SENDER")
    private User idUserSender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER_RECIVER")
    private User idUserReciver;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public User getIdUserSender() {
        return idUserSender;
    }

    public void setIdUserSender(User idUserSender) {
        this.idUserSender = idUserSender;
    }

    public User getIdUserReciver() {
        return idUserReciver;
    }

    public void setIdUserReciver(User idUserReciver) {
        this.idUserReciver = idUserReciver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}