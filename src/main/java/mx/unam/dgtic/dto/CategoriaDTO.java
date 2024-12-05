package mx.unam.dgtic.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Categoria DTO es un objeto para manejar datos hacia el front
 *
 * @autor Alejandro Noyola
 */
public class CategoriaDTO {

    @NotNull(message = "El ID de la categoría no debe ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private int idCategoria;

    @NotBlank(message = "El nombre de la categoría no debe estar vacío")
    private String categoria;

    @NotBlank(message = "La abreviatura de la categoría no debe estar vacía")
    private String abreviatura;

    @JsonManagedReference
    private Set<ElectronicoDTO> electronicos = new HashSet<>();

    /**
     * Este constructor genera un objeto Categoria vacío
     */
    public CategoriaDTO() {
    }

    /**
     * Constructor de la clase CategoriaDTO
     *
     * @param idCategoria  ID de la categoría
     * @param categoria    Nombre de la categoría
     * @param abreviatura  Abreviatura de la categoría
     */
    public CategoriaDTO(int idCategoria, String categoria, String abreviatura) {
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

    public Set<ElectronicoDTO> getElectronicos() {
        return electronicos;
    }

    public void setElectronicos(Set<ElectronicoDTO> electronicos) {
        this.electronicos = electronicos;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "idCategoria=" + idCategoria +
                ", categoria='" + categoria + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                ", electronicos=" + electronicos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDTO that = (CategoriaDTO) o;
        return idCategoria == that.idCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria);
    }
}
