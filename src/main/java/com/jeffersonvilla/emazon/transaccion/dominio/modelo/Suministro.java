package com.jeffersonvilla.emazon.transaccion.dominio.modelo;

import java.time.LocalDate;

public class Suministro {

    private Long id;
    private Long idArticulo;
    private Integer cantidad;
    private Long idUsuario;
    private LocalDate fechaCreacion;

    public Long getId() {
        return id;
    }

    public Long getIdArticulo() {
        return idArticulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Long getIdUsuario(){
        return idUsuario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    private Suministro(SuministroBuilder builder){
        this.id = builder.id;
        this.idArticulo = builder.idArticulo;
        this.cantidad = builder.cantidad;
        this.fechaCreacion = builder.fechaCreacion;
        this.idUsuario = builder.idUsuario;
    }

    public static class SuministroBuilder{

        private Long id = null;
        private Long idArticulo = null;
        private Integer cantidad = null;
        private Long idUsuario = null;
        private LocalDate fechaCreacion = null;

        public Suministro build(){
            return new Suministro(this);
        }

        public SuministroBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public SuministroBuilder setIdArticulo(Long idArticulo) {
            this.idArticulo = idArticulo;
            return this;
        }

        public SuministroBuilder setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public SuministroBuilder setIdUsuario(Long idUsuario){
            this.idUsuario = idUsuario;
            return this;
        }

        public SuministroBuilder setFechaCreacion(LocalDate fechaCreacion){
            this.fechaCreacion = fechaCreacion;
            return this;
        }
    }
}
