package mx.unam.dgtic.system.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    protected int id;
    protected String proveedor;

    @ManyToMany(mappedBy = "proveedores", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "proveedores")
    private Collection<Electronico> electronicos;


    public Proveedor() {
        electronicos = new ArrayList<Electronico>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Collection<Electronico> getElectronicos() {
        return electronicos;
    }

    public void addElectronico(Electronico electronico) {
        if (!getElectronicos().contains(electronico)) {
            getElectronicos().add(electronico);
        }
        if (!electronico.getProveedores().contains(this)) {
            electronico.getProveedores().add(this);
        }
    }

    public void removeElectronico(Electronico electronico) {
        if (electronicos.contains(electronico)) {
            electronicos.remove(electronico);
            electronico.getProveedores().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", proveedor='" + proveedor + '\'' +
                ", con " + getElectronicos().size() + " electronicos " +
                '}';
    }
}
