package mx.unam.dgtic.auth.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "matricula")
@Table(name = "Electronicos")
@NamedQueries({
        @NamedQuery(name = "Electronico.buscarTodosConMarcas",
                query = "SELECT DISTINCT a FROM Electronico a JOIN FETCH a.marcas"),
        @NamedQuery(name = "Electronico.findDistinctByCategoria",
                query = "SELECT DISTINCT a.categoria FROM Electronico a"),
        @NamedQuery(name = "Electronico.countByCategoria",
                query = "SELECT a.categoria, COUNT(a) FROM Electronico a GROUP BY a.categoria")
})

public class Electronico {
    @Id
    private String matricula;
    private String nombre;
    private String codigo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ffac;
    private double precio;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @JsonManagedReference
    @OneToMany(mappedBy = "electronico")
    private List<Marca> marcas = new ArrayList<Marca>();

    @ManyToMany
    @JoinTable(
            name = "Electronicos_Proveedores",
            joinColumns = @JoinColumn(name = "matricula", referencedColumnName = "matricula"),
            inverseJoinColumns = @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    )
    @JsonIgnoreProperties(value = "electronicos")
    private List<Proveedor> proveedores = new ArrayList<>();

    public Electronico() {
    }

    public Electronico(String matricula) {
        this.matricula = matricula;
    }

    public Electronico(String matricula, String nombre, String codigo, Date ffac, double precio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.codigo = codigo;
        this.ffac = ffac;
        this.precio = precio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFfac() {
        return ffac;
    }

    public void setFfac(Date ffac) {
        this.ffac = ffac;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void addProveedor(Proveedor proveedor) {
        if (!proveedores.contains(proveedor)) {
            proveedores.add(proveedor);
            proveedor.addElectronico(this);
        }
    }

    public void removeProveedor(Proveedor proveedor) {
        if (proveedores.contains(proveedor)) {
            proveedores.remove(proveedor);
            proveedor.removeElectronico(this);
        }
    }

    @Override
    public String toString() {
        String categoriaStr = (getCategoria() != null) ? getCategoria().getCategoria() : "null";
        return "Electronico{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", ffac=" + ffac +
                ", precio=" + precio +
                ", categoria=" + categoriaStr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Electronico electronico= (Electronico) o;
        return matricula.equals(electronico.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
