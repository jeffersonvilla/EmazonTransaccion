package com.jeffersonvilla.emazon.transaccion.dominio.spi;

import com.jeffersonvilla.emazon.transaccion.dominio.modelo.Suministro;

public interface IStockApiPort {

    void agregarSuministro(Suministro suministro);
}
