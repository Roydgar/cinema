package tk.roydgar.cinema.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime time;

    @OneToOne(cascade = CascadeType.MERGE)
    private Movie movie;

    @OneToOne(cascade = CascadeType.MERGE)
    private Hall hall;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<User> users = new ArrayList<>();


}
