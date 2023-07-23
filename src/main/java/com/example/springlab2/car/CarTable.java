package com.example.springlab2.car;

import javax.persistence.*;

@Entity
@Table(name = "car_table", schema = "users", catalog = "")
public class CarTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcar_table")
    private int idcarTable;
    @Basic
    @Column(name = "plateref")
    private String plateref;
    @Basic
    @Column(name = "id_user")
    private Integer idUser;
    @Basic
    @Column(name = "car_color")
    private String carColor;

    @Basic
    @Column(name = "car_model")
    private String carModel;

    @Id
    @Column(name = "idcar_table")
    public int getIdcarTable() {
        return idcarTable;
    }

    public void setIdcarTable(int idcarTable) {
        this.idcarTable = idcarTable;
    }

    @Basic
    @Column(name = "plateref")
    public String getPlateref() {
        return plateref;
    }

    public void setPlateref(String plateref) {
        this.plateref = plateref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarTable carTable = (CarTable) o;

        if (idcarTable != carTable.idcarTable) return false;
        if (plateref != null ? !plateref.equals(carTable.plateref) : carTable.plateref != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcarTable;
        result = 31 * result + (plateref != null ? plateref.hashCode() : 0);
        return result;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
