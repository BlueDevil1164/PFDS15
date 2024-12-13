package mx.unam.dgtic.auth.dto;

import jakarta.validation.constraints.*;

import java.util.Objects;

/**
 * CompradorDTO es un objeto para manejar datos hacia el front.
 *
 * @autor Alejandro Noyola
 */
public class VendedorDTO {

    @NotNull(message = "El ID no debe ser nulo")
    @Positive(message = "El ID debe ser un valor positivo")
    private int id; // ID único para el comprador

    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos no deben estar en blanco")
    @Size(max = 100, message = "Los apellidos no deben exceder los 100 caracteres")
    private String apellidos;

    @NotNull(message = "La edad no debe ser nula")
    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 120, message = "La edad máxima debe ser 120")
    private int edad;

    @NotBlank(message = "El género no debe estar en blanco")
    @Pattern(regexp = "^(Masculino|Femenino|Otro)$",
            message = "El género debe ser Masculino, Femenino u Otro")
    private String genero;

    /**
     * Constructor vacío para inicialización predeterminada.
     */
    public VendedorDTO() {
    }

    /**
     * Constructor con parámetros para inicializar un CompradorDTO.
     *
     * @param id ID único del comprador
     * @param nombre Nombre del comprador
     * @param apellidos Apellidos del comprador
     * @param edad Edad del comprador
     * @param genero Género del comprador
     */
    public VendedorDTO(int id, String nombre, String apellidos, int edad, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "CompradorDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendedorDTO that = (VendedorDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
