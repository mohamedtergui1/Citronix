package ma.tr.citronix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "farms")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
