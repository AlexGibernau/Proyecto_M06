package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    public static User from(String stringifiedUser) throws IllegalArgumentException {
        User user = new User();
        Pattern pattern = Pattern.compile("User\\{id=(\\d+), name='(.*?)'\\}");
        Matcher matcher = pattern.matcher(stringifiedUser);

        if (matcher.find()) {
            user.setId(Long.parseLong(matcher.group(1)));
            user.setName((matcher.group(2)));
        } else {
            throw new IllegalArgumentException("Invalid message: " + stringifiedUser);
        }

        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}