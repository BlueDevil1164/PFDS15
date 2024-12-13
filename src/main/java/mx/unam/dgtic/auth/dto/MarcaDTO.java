package mx.unam.dgtic.auth.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Objects;

/**
 * Marca DTO es un objeto para manejar datos hacia el front
 *
 * @autor Alejandro Noyola
 */
public class MarcaDTO {

    @NotNull(message = "El ID de la marca no debe ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private int id;

    @NotBlank(message = "El nombre de la marca no debe estar vacío")
    private String nombre;

    @Min(value = 0, message = "La tasa debe ser un valor mayor o igual a 0")
    @Max(value = 100, message = "La tasa debe ser un valor menor o igual a 100")
    private int rate;

    @JsonBackReference
    private String electronicoMatricula;  // Esto solo se usa para manejar la relación con Electronico

    /**
     * Constructor vacío
     */
    public MarcaDTO() {
    }

    /**
     * Constructor de la clase MarcaDTO
     *
     * @param id       ID de la marca
     * @param nombre   Nombre de la marca
     * @param rate     Tasa de la marca
     */
    public MarcaDTO(int id, String nombre, int rate) {
        this.id = id;
        this.nombre = nombre;
        this.rate = rate;
    }

    /**
     * Constructor con datos completos, incluyendo el producto relacionado
     *
     * @param id                 ID de la marca
     * @param nombre             Nombre de la marca
     * @param rate               Tasa de la marca
     * @param electronicoMatricula Matricula del electrónico relacionado
     */
    public MarcaDTO(int id, String nombre, int rate, String electronicoMatricula) {
        this.id = id;
        this.nombre = nombre;
        this.rate = rate;
        this.electronicoMatricula = electronicoMatricula;
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

    public String getElectronicoMatricula() {
        return electronicoMatricula;
    }

    public void setElectronicoMatricula(String electronicoMatricula) {
        this.electronicoMatricula = electronicoMatricula;
    }

    @Override
    public String toString() {
        return "MarcaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rate=" + rate +
                ", electronicoMatricula='" + electronicoMatricula + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarcaDTO marcaDTO = (MarcaDTO) o;
        return id == marcaDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
