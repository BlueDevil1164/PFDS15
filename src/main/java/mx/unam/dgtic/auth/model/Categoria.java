package mx.unam.dgtic.auth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Categorias")
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    private int idCategoria;
    private String categoria;
    private String abreviatura;

    @JsonManagedReference
    @OneToMany(mappedBy = "categoria")
    private Set<Electronico> electronicos = new HashSet<>();

    public Categoria() {
    }

    public Categoria(int idCategoria, String categoria, String abreviatura) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.abreviatura = abreviatura;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Set<Electronico> getElectronicos() {
        return electronicos;
    }

    public void setElectronicos(Set<Electronico> electronicos) {this.electronicos = electronicos;}

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", categoria='" + categoria + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                '}';
    }
}

