package api_usuario_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario {

    @Id
    @Column(name="id")
    private String usuarioId;

    private String nombre;

    private String email;

    private String informacion;

    @Transient
    private List<Calificacion> calificaciones = new ArrayList<>();

}
