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
package com.root101.module.gestion.moneda.core.domain;

import static com.root101.module.gestion.moneda.service.ResourceKeysStandard.*;
import com.root101.utils.clean.EntityDomainObjectValidated;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MonedaDomain extends EntityDomainObjectValidated {

    private Integer idMoneda;

    @NotEmpty(message = VALIDATION_MONEDA_MONEDA_NOMBRE_VACIO)
    @Size(max = 3, message = VALIDATION_MONEDA_MONEDA_NOMBRE_LARGO)
    private String nombreMoneda;

    @PositiveOrZero(message = VALIDATION_MONEDA_MONEDA_COMPRA_NEGATIVO)
    @Max(value = Long.MAX_VALUE, message = VALIDATION_MONEDA_MONEDA_VALOR_MUY_GRANDE)
    private BigDecimal compra;

    @PositiveOrZero(message = VALIDATION_MONEDA_MONEDA_VENTA_NEGATIVO)
    @Max(value = Long.MAX_VALUE, message = VALIDATION_MONEDA_MONEDA_VALOR_MUY_GRANDE)
    private BigDecimal venta;

    @Size(max = 495, message = VALIDATION_MONEDA_DESCRIPCION_LARGA)
    private String descripcion;

    public MonedaDomain() {
    }

    public MonedaDomain(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public MonedaDomain(String nombreMoneda, BigDecimal compra, BigDecimal venta, String descripcion) {
        this.nombreMoneda = nombreMoneda;
        this.compra = compra;
        this.venta = venta;
        this.descripcion = descripcion;
        validate();
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
        if (!(object instanceof MonedaDomain)) {
            return false;
        }
        MonedaDomain other = (MonedaDomain) object;
        if ((this.idMoneda == null && other.idMoneda != null) || (this.idMoneda != null && !this.idMoneda.equals(other.idMoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreMoneda;
    }

}
