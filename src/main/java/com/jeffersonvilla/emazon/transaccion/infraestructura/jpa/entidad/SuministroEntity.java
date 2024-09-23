package com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suministro")
public class SuministroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suministro", columnDefinition = "INT")
    private Long id;

    @Column(name = "id_articulo", columnDefinition = "INT", nullable = false)
    private Long idArticulo;

    @Column(name = "cantidad", columnDefinition = "INT", nullable = false)
    private Integer cantidad;

    @Column(name = "id_usuario", columnDefinition = "INT", nullable = false)
    private Long idUsuario;

    @Column(name = "fecha_creacion", columnDefinition = "DATE", nullable = false)
    private LocalDate fechaCreacion;
}
