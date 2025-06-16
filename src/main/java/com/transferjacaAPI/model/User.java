package com.transferjacaAPI.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

    public User() {
    }

    public User(Long id, String username, String email, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    // hashCode y equals usando id

    public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }
}
