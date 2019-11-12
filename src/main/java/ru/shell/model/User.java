package ru.shell.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }


    @Column(name = "age")
    private Long age;
    public void setAge(Long age) { this.age = age; }
    public Long getAge() {
        return age;
    }


    @Column(name = "name")
    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }


    @Column(name = "password")
    private String password;
    public void setPassword(String password) { this.password = password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String userRole = getRoles().toString();
//        GrantedAuthority authority = new SimpleGrantedAuthority(userRole);
//        return Collections.singletonList(authority);
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"), //указывает на поле, которое используется для прямой связи
            inverseJoinColumns = @JoinColumn(name = "role_id")) //указывает на поле, которое используется для обратной связи
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER) //объекты коллекции сразу загружаются в память
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {

    }

    public User(Long age, String name, String password, Set<Role> roles) {
        this.age = age;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(age, user.age) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getName(), getPassword());
    }
}



//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    public void setId(int id) { this.id = id; }
//    public int getId() { return id; }
//
//
//    @Column(name = "age")
//    private Long age;
//    public void setAge(Long age) { this.age = age; }
//    public Long getAge() {
//        return age;
//    }
//
//
//    @Column(name = "name")
//    private String name;
//    public void setName(String name) { this.name = name; }
//    public String getName() { return name; }
//
//
//    @Column(name = "password")
//    private String password;
//    public void setPassword(String password) { this.password = password; }
//    public String getPassword() {
//        return password;
//    }
//
////    @ManyToOne
////    @JoinColumn(name="role_id")   //   @OneToMany(mappedBy="role")
//    @Column(name = "role")
//    private String role;   //множество
//    public void setRole(String role) { this.role = role; }
//    public String getRole() { return role; }
//
//
//    public User() {
//
//    }
//
//    public User(Long age, String name, String password, String role) {
//        this.age = age;
//        this.name = name;
//        this.password = password;
//        this.role = role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User that = (User) o;
//        return Objects.equals(getAge(), that.getAge()) &&
//                Objects.equals(getName(), that.getName()) &&
//                Objects.equals(getPassword(), that.getPassword()) &&
//                Objects.equals(getRole(), that.getRole());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getAge(), getName(), getPassword());
//    }
//}