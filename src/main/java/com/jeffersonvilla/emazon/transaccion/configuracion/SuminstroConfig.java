package com.jeffersonvilla.emazon.transaccion.configuracion;

import com.jeffersonvilla.emazon.transaccion.dominio.api.ISuministroServicePort;
import com.jeffersonvilla.emazon.transaccion.dominio.api.servicio.SuministroCasoUso;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IStockApiPort;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.ISuministroPersistenciaPort;
import com.jeffersonvilla.emazon.transaccion.dominio.spi.IUsuarioJwtPort;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.adaptador.StockApiAdapter;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.cliente.StockFeignClient;
import com.jeffersonvilla.emazon.transaccion.infraestructura.feign.mapper.SuministroMapperFeign;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.mapper.SuministroMapperJpa;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.repositorio.SuministroPersistenciaJpa;
import com.jeffersonvilla.emazon.transaccion.infraestructura.jpa.repositorio.SumnistroRepository;
import com.jeffersonvilla.emazon.transaccion.infraestructura.seguridad.UsuarioJwtAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SuminstroConfig {

    private final StockFeignClient stockFeignClient;
    private final SuministroMapperFeign mapperFeign;
    private final SumnistroRepository sumnistroRepository;
    private final SuministroMapperJpa mapperJpa;

    @Bean
    public ISuministroServicePort suministroServicePort(){
        return new SuministroCasoUso(stockApiPort(), suministroPersistenciaPort(), usuarioJwtPort());
    }

    @Bean
    public IStockApiPort stockApiPort(){
        return new StockApiAdapter(stockFeignClient, mapperFeign);
    }

    @Bean
    public ISuministroPersistenciaPort suministroPersistenciaPort(){
        return new SuministroPersistenciaJpa(sumnistroRepository, mapperJpa);
    }

    @Bean
    public IUsuarioJwtPort usuarioJwtPort(){
        return new UsuarioJwtAdapter();
    }
}
