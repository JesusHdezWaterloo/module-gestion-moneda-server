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
package com.root101.module.gestion.moneda.repo.entities;

import com.root101.module.gestion.moneda.repo.utils.ResourcesMoneda;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "moneda", schema = ResourcesMoneda.SCHEMA,
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"nombre_moneda"})})
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
    @NamedQuery(name = "Moneda.findByIdMoneda", query = "SELECT m FROM Moneda m WHERE m.idMoneda = :idMoneda"),
    @NamedQuery(name = "Moneda.findByNombreMoneda", query = "SELECT m FROM Moneda m WHERE m.nombreMoneda = :nombreMoneda"),
    @NamedQuery(name = "Moneda.findByCompra", query = "SELECT m FROM Moneda m WHERE m.compra = :compra"),
    @NamedQuery(name = "Moneda.findByVenta", query = "SELECT m FROM Moneda m WHERE m.venta = :venta"),
    @NamedQuery(name = "Moneda.findByDescripcion", query = "SELECT m FROM Moneda m WHERE m.descripcion = :descripcion")})
public class Moneda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_moneda", nullable = false)
    private Integer idMoneda;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "nombre_moneda", nullable = false, length = 5)
    private String nombreMoneda;

    @Basic(optional = false)
    @NotNull
    @Column(name = "compra", nullable = false, precision = 19, scale = 4)
    @PositiveOrZero
    @Max(value = Long.MAX_VALUE)
    private BigDecimal compra;

    @Basic(optional = false)
    @NotNull
    @Column(name = "venta", nullable = false, precision = 19, scale = 4)
    @PositiveOrZero
    @Max(value = Long.MAX_VALUE)
    private BigDecimal venta;

    @Basic(optional = false)
    @NotNull
    @Size(max = 500)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    public Moneda() {
    }

    public Moneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Moneda(Integer idMoneda, String nombreMoneda, BigDecimal compra, BigDecimal venta, String descripcion) {
        this.idMoneda = idMoneda;
        this.nombreMoneda = nombreMoneda;
        this.compra = compra;
        this.venta = venta;
        this.descripcion = descripcion;
    }

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public BigDecimal getCompra() {
        return compra;
    }

    public void setCompra(BigDecimal compra) {
        this.compra = compra;
    }

    public BigDecimal getVenta() {
        return venta;
    }

    public void setVenta(BigDecimal venta) {
        this.venta = venta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoneda != null ? idMoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.idMoneda == null && other.idMoneda != null) || (this.idMoneda != null && !this.idMoneda.equals(other.idMoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testJPA.entities.contabilidad_empresarial.Moneda[ idMoneda=" + idMoneda + " ]";
    }

}
