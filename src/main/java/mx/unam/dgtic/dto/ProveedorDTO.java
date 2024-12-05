package mx.unam.dgtic.dto;

import java.util.Objects;
import java.util.Set;

/**
 * Proveedor DTO es un objeto para manejar datos hacia el front
 *
 * @autor Alejandro Noyola
 */
public class ProveedorDTO {

    private int id;
    private String proveedor;
    private Set<String> electronicos;  // Listar las matrículas o identificadores de los electronicos asociados

    /**
     * Constructor vacío
     */
    public ProveedorDTO() {
    }

    /**
     * Constructor con datos completos
     *
     * @param id         ID del proveedor
     * @param proveedor  Nombre del proveedor
     * @param electronicos Set de matrículas de electronicos relacionados
     */
    public ProveedorDTO(int id, String proveedor, Set<String> electronicos) {
        this.id = id;
        this.proveedor = proveedor;
        this.electronicos = electronicos;
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

    public Set<String> getElectronicos() {
        return electronicos;
    }

    public void setElectronicos(Set<String> electronicos) {
        this.electronicos = electronicos;
    }

    @Override
    public String toString() {
        return "ProveedorDTO{" +
                "id=" + id +
                ", proveedor='" + proveedor + '\'' +
                ", electronicos=" + electronicos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProveedorDTO that = (ProveedorDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
