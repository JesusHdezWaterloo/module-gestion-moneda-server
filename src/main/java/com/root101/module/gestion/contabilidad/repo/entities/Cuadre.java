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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Entity
@Table(name = "cuadre", schema = ResourcesContabilidad.SCHEMA,
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"operacion_contable_fk", "operacion_contable_cuadre_fk"})})
@NamedQueries({
    @NamedQuery(name = "Cuadre.findAll", query = "SELECT c FROM Cuadre c"),
    @NamedQuery(name = "Cuadre.findByIdCuadre", query = "SELECT c FROM Cuadre c WHERE c.idCuadre = :idCuadre"),
    @NamedQuery(name = "Cuadre.findByLiquidada", query = "SELECT c FROM Cuadre c WHERE c.liquidada = :liquidada")
})
public class Cuadre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuadre", nullable = false)
    private Integer idCuadre;

    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidada", nullable = false)
    private boolean liquidada;

    @JoinColumn(name = "operacion_contable_cuadre_fk", referencedColumnName = "id_operacion_contable", nullable = false)
    @ManyToOne(optional = false)
    private OperacionContable operacionContableCuadreFk;

    @JoinColumn(name = "operacion_contable_fk", referencedColumnName = "id_operacion_contable", nullable = false)
    @ManyToOne(optional = false)
    private OperacionContable operacionContableFk;

    public Cuadre() {
    }

    public Cuadre(Integer idCuadre) {
        this.idCuadre = idCuadre;
    }

    public Cuadre(Integer idCuadre, boolean liquidada) {
        this.idCuadre = idCuadre;
        this.liquidada = liquidada;
    }

    public Integer getIdCuadre() {
        return idCuadre;
    }

    public void setIdCuadre(Integer idCuadre) {
        this.idCuadre = idCuadre;
    }

    public boolean getLiquidada() {
        return liquidada;
    }

    public void setLiquidada(boolean liquidada) {
        this.liquidada = liquidada;
    }

    public OperacionContable getOperacionContableCuadreFk() {
        return operacionContableCuadreFk;
    }

    public void setOperacionContableCuadreFk(OperacionContable operacionContableCuadreFk) {
        this.operacionContableCuadreFk = operacionContableCuadreFk;
    }

    public OperacionContable getOperacionContableFk() {
        return operacionContableFk;
    }

    public void setOperacionContableFk(OperacionContable operacionContableFk) {
        this.operacionContableFk = operacionContableFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuadre != null ? idCuadre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuadre)) {
            return false;
        }
        Cuadre other = (Cuadre) object;
        if ((this.idCuadre == null && other.idCuadre != null) || (this.idCuadre != null && !this.idCuadre.equals(other.idCuadre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testJPA.entities.contabilidad_empresarial.Cuadre[ idCuadre=" + idCuadre + " ]";
    }

}
