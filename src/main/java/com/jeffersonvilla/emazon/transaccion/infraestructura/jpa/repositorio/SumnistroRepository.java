package com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.repositorio;

import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.entidad.SuministroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SumnistroRepository extends JpaRepository<SuministroEntity, Long> {
}
