package mx.unam.dgtic.system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int rate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "electronicos_matricula")
    private Electronico electronico;

    public Marca() {
    }

    public Marca(int id) {
        this.id = id;
    }

    public Marca(String nombre, int rate) {
        this.nombre = nombre;
        this.rate = rate;
    }

    public Marca(int id, String materia, int calificacion) {
        this.id = id;
        this.nombre = nombre;
        this.rate = rate;
    }

    public Marca(int id, String nombre, int rate, Electronico electronico) {
        this.id = id;
        this.nombre = nombre;
        this.rate = rate;
        this.electronico = electronico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Electronico getElectronico() {
        return electronico;
    }

    public void setElectronico(Electronico electronico) {
        this.electronico = electronico;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rate=" + rate +
                ", electronico=" + electronico +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca that = (Marca) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

