package com.example.springlab2.emptyplaces;

import javax.persistence.*;

@Entity
@Table(name = "empty_places", schema = "users", catalog = "")
public class EmptyPlaces {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempty_places")
    private int idemptyPlaces;
    @Basic
    @Column(name = "places")
    private int places;

    public int getIdemptyPlaces() {
        return idemptyPlaces;
    }

    public void setIdemptyPlaces(int idemptyPlaces) {
        this.idemptyPlaces = idemptyPlaces;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmptyPlaces that = (EmptyPlaces) o;

        if (idemptyPlaces != that.idemptyPlaces) return false;
        if (places != that.places) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idemptyPlaces;
        result = 31 * result + places;
        return result;
    }
}
