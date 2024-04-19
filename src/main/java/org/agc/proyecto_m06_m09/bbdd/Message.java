package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGES_SEQ")
    @SequenceGenerator(name = "MESSAGES_SEQ", sequenceName = "MESSAGES_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FROM", referencedColumnName = "ID")
    private User idFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TO", referencedColumnName = "ID")
    private User idTo;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DATE_TIME", length = 30)
    private String dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(User idFrom) {
        this.idFrom = idFrom;
    }

    public User getIdTo() {
        return idTo;
    }

    public void setIdTo(User idTo) {
        this.idTo = idTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", idFrom=" + idFrom +
                ", idTo=" + idTo +
                ", message='" + message + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}