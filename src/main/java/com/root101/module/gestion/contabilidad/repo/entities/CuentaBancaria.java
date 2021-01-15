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
import javax.persistence.UniqueConstraint;
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
@Table(name = "cuenta_bancaria", schema = ResourcesContabilidad.SCHEMA,
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"codigo"}),
            @UniqueConstraint(columnNames = {"numero_tarjeta"}),
            @UniqueConstraint(columnNames = {"numero_cuenta"}),
            @UniqueConstraint(columnNames = {"nombre_cuenta"})})
@NamedQueries({
    @NamedQuery(name = "CuentaBancaria.findAll", query = "SELECT c FROM CuentaBancaria c"),
    @NamedQuery(name = "CuentaBancaria.findByIdCuentaBancaria", query = "SELECT c FROM CuentaBancaria c WHERE c.idCuentaBancaria = :idCuentaBancaria"),
    @NamedQuery(name = "CuentaBancaria.findByNombreCuenta", query = "SELECT c FROM CuentaBancaria c WHERE c.nombreCuenta = :nombreCuenta"),
    @NamedQuery(name = "CuentaBancaria.findByNumeroCuenta", query = "SELECT c FROM CuentaBancaria c WHERE c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "CuentaBancaria.findByNumeroTarjeta", query = "SELECT c FROM CuentaBancaria c WHERE c.numeroTarjeta = :numeroTarjeta"),
    @NamedQuery(name = "CuentaBancaria.findByPin", query = "SELECT c FROM CuentaBancaria c WHERE c.pin = :pin"),
    @NamedQuery(name = "CuentaBancaria.findByCodigo", query = "SELECT c FROM CuentaBancaria c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CuentaBancaria.findByDebito", query = "SELECT c FROM CuentaBancaria c WHERE c.debito = :debito"),
    @NamedQuery(name = "CuentaBancaria.findByCredito", query = "SELECT c FROM CuentaBancaria c WHERE c.credito = :credito"),
    @NamedQuery(name = "CuentaBancaria.findByDescripcion", query = "SELECT c FROM CuentaBancaria c WHERE c.descripcion = :descripcion")})
public class CuentaBancaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta_bancaria", nullable = false)
    private Integer idCuentaBancaria;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_cuenta", nullable = false, length = 100)
    private String nombreCuenta;

    @Basic(optional = false)
    @NotNull
    @Size(min = 16, max = 16)
    @Column(name = "numero_cuenta", nullable = false, length = 16)
    private String numeroCuenta;

    @Basic(optional = false)
    @NotNull
    @Size(max = 16)
    @Column(name = "numero_tarjeta", nullable = false, length = 16)
    private String numeroTarjeta;

    @Basic(optional = false)
    @NotNull
    @Size(max = 4)
    @Column(name = "pin", nullable = false, length = 4)
    private String pin;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo", nullable = false, length = 5)
    private String codigo;

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
    @Size(min = 0, max = 500)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @JoinColumn(name = "moneda_fk", referencedColumnName = "id_moneda", nullable = false)
    @ManyToOne(optional = false)
    private Moneda monedaFk;

    public CuentaBancaria() {
    }

    public CuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public CuentaBancaria(Integer idCuentaBancaria, String nombreCuenta, String numeroCuenta, String numeroTarjeta, String pin, String codigo, BigDecimal debito, BigDecimal credito, String descripcion) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.nombreCuenta = nombreCuenta;
        this.numeroCuenta = numeroCuenta;
        this.numeroTarjeta = numeroTarjeta;
        this.pin = pin;
        this.codigo = codigo;
        this.debito = debito;
        this.credito = credito;
        this.descripcion = descripcion;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Moneda getMonedaFk() {
        return monedaFk;
    }

    public void setMonedaFk(Moneda monedaFk) {
        this.monedaFk = monedaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuentaBancaria != null ? idCuentaBancaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBancaria)) {
            return false;
        }
        CuentaBancaria other = (CuentaBancaria) object;
        if ((this.idCuentaBancaria == null && other.idCuentaBancaria != null) || (this.idCuentaBancaria != null && !this.idCuentaBancaria.equals(other.idCuentaBancaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testJPA.entities.contabilidad_empresarial.CuentaBancaria[ idCuentaBancaria=" + idCuentaBancaria + " ]";
    }

}
