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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Entity
@Table(name = "operacion_contable", schema = ResourcesContabilidad.SCHEMA)
@NamedQueries({
    @NamedQuery(name = "OperacionContable.findAll", query = "SELECT o FROM OperacionContable o"),
    @NamedQuery(name = "OperacionContable.findByIdOperacionContable", query = "SELECT o FROM OperacionContable o WHERE o.idOperacionContable = :idOperacionContable"),
    @NamedQuery(name = "OperacionContable.findByDebito", query = "SELECT o FROM OperacionContable o WHERE o.debito = :debito"),
    @NamedQuery(name = "OperacionContable.findByCredito", query = "SELECT o FROM OperacionContable o WHERE o.credito = :credito")})
public class OperacionContable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_operacion_contable", nullable = false)
    private Integer idOperacionContable;

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

    @JoinColumn(name = "cuenta_fk", referencedColumnName = "id_cuenta_contable", nullable = false)
    @ManyToOne(optional = false)
    private CuentaContable cuentaFk;

    @JoinColumn(name = "info_operacion_contable_fk", referencedColumnName = "id_info_operacion_contable", nullable = false)
    @ManyToOne(optional = false)
    private InfoOperacionContable infoOperacionContableFk;

    public OperacionContable() {
    }

    public OperacionContable(Integer idOperacionContable) {
        this.idOperacionContable = idOperacionContable;
    }

    public OperacionContable(Integer idOperacionContable, BigDecimal debito, BigDecimal credito) {
        this.idOperacionContable = idOperacionContable;
        this.debito = debito;
        this.credito = credito;
    }

    public Integer getIdOperacionContable() {
        return idOperacionContable;
    }

    public void setIdOperacionContable(Integer idOperacionContable) {
        this.idOperacionContable = idOperacionContable;
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

    public CuentaContable getCuentaFk() {
        return cuentaFk;
    }

    public void setCuentaFk(CuentaContable cuentaFk) {
        this.cuentaFk = cuentaFk;
    }

    public InfoOperacionContable getInfoOperacionContableFk() {
        return infoOperacionContableFk;
    }

    public void setInfoOperacionContableFk(InfoOperacionContable infoOperacionContableFk) {
        this.infoOperacionContableFk = infoOperacionContableFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacionContable != null ? idOperacionContable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperacionContable)) {
            return false;
        }
        OperacionContable other = (OperacionContable) object;
        if ((this.idOperacionContable == null && other.idOperacionContable != null) || (this.idOperacionContable != null && !this.idOperacionContable.equals(other.idOperacionContable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testJPA.entities.contabilidad_empresarial.OperacionContable[ idOperacionContable=" + idOperacionContable + " ]";
    }

}
