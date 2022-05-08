package com.akoca.producterbasketballapi.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Player> players = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;

        return id != null && id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return 1948895937;
    }
}
