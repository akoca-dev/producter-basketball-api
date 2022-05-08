package com.akoca.producterbasketballapi.entity;

import com.akoca.producterbasketballapi.enums.Position;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Player player = (Player) o;

        return id != null && id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return 1467942173;
    }
}
