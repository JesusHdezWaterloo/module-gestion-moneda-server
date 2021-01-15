/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.gestion.contabilidad.repo.entities;

import com.root101.module.gestion.contabilidad.repo.utils.ResourcesContabilidad;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Entity
@Table(name = "liquidacion", schema = ResourcesContabilidad.SCHEMA)
@NamedQueries({
    @NamedQuery(name = "Liquidacion.findAll", query = "SELECT l FROM Liquidacion l"),
    @NamedQuery(name = "Liquidacion.findByIdLiquidacion", query = "SELECT l FROM Liquidacion l WHERE l.idLiquidacion = :idLiquidacion"),
    @NamedQuery(name = "Liquidacion.findByDocumento", query = "SELECT l FROM Liquidacion l WHERE l.documento = :documento"),
    @NamedQuery(name = "Liquidacion.findByNombre", query = "SELECT l FROM Liquidacion l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Liquidacion.findByDebito", query = "SELECT l FROM Liquidacion l WHERE l.debito = :debito"),
    @NamedQuery(name = "Liquidacion.findByCredito", query = "SELECT l FROM Liquidacion l WHERE l.credito = :credito"),
    @NamedQuery(name = "Liquidacion.findByFecha", query = "SELECT l FROM Liquidacion l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "Liquidacion.findByDescripcion", query = "SELECT l FROM Liquidacion l WHERE l.descripcion = :descripcion")})
public class Liquidacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_liquidacion", nullable = false)
    private Integer idLiquidacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "documento", nullable = false, length = 100)
    private String documento;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Column(name = "debito", nullable = false, precision = 19, scale = 4)
    @PositiveOrZero
    @Max(value = Long.MAX_VALUE)
    private BigDecimal debito;

    @Basic(optional = false)
    @NotNull
    @Column(name = "credito", nullable = false, precision = 19, scale = 4)
    @PositiveOrZero
    @Max(value = Long.MAX_VALUE)
    private BigDecimal credito;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 500)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @JoinColumn(name = "cuenta_fk", referencedColumnName = "id_cuenta_bancaria", nullable = false)
    @ManyToOne(optional = false)
    private CuentaBancaria cuentaFk;

    @JoinColumn(name = "cuadre_fk", referencedColumnName = "id_cuadre", nullable = false)
    @ManyToOne(optional = false)
    private Cuadre cuadreFk;

    public Liquidacion() {
    }

    public Liquidacion(Integer idOperacionBancaria) {
        this.idLiquidacion = idOperacionBancaria;
    }

    public Liquidacion(Integer idOperacionBancaria, String documento, String nombre, BigDecimal debito, BigDecimal credito, LocalDate fecha, String descripcion) {
        this.idLiquidacion = idOperacionBancaria;
        this.documento = documento;
        this.nombre = nombre;
        this.debito = debito;
        this.credito = credito;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Integer getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(Integer idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuadre getCuadreFk() {
        return cuadreFk;
    }

    public void setCuadreFk(Cuadre cuadreFk) {
        this.cuadreFk = cuadreFk;
    }

    public CuentaBancaria getCuentaFk() {
        return cuentaFk;
    }

    public void setCuentaFk(CuentaBancaria cuentaFk) {
        this.cuentaFk = cuentaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLiquidacion != null ? idLiquidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liquidacion)) {
            return false;
        }
        Liquidacion other = (Liquidacion) object;
        if ((this.idLiquidacion == null && other.idLiquidacion != null) || (this.idLiquidacion != null && !this.idLiquidacion.equals(other.idLiquidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testJPA.entities.contabilidad_empresarial.Liquidacion[ idOperacionBancaria=" + idLiquidacion + " ]";
    }

}
