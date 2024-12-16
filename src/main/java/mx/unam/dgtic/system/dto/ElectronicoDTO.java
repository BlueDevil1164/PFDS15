package mx.unam.dgtic.system.dto;

import jakarta.validation.constraints.*;

import java.util.Objects;

/**
* Alumno DTO es un objeto para manejar datos hacia el front
*
* @autor Alejandro Noyola
*/
public class ElectronicoDTO {

    @NotNull(message = "La matricula no debe ser nula")
    @NotBlank(message = "La matricula no debe ser texto en blanco")
    private String matricula;

    @NotBlank(message = "El nombre del alumno no debe ser blanco")
    private String nombre;

    @NotBlank(message = "El CODIGO no debe ser blanco")
    private String codigo;

    //@Past(message = "La fecha debe ser en pasado")-> Entity
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "El formato de la fecha es del tipo AAAA-MM-DD")
    @NotBlank(message = "La fecha no puede estar en blanco")
    private String ffac;

    @Positive(message = "La estatura debe ser un valor positivo")
    @DecimalMin(value = "0.0", message = "El precio no debe ser menor de 0.0 PESOS")
    @DecimalMax(value = "10000.0", message = "El precio no debe pasar de 10000.0 PESOS")
    private double precio;


    @NotNull(message = "Debes proporcionar una categoria")
    @NotBlank(message = "Debes proporcionar un texto con el nombre de la categoria")
    private String categoria;

    /**
     * Este constuctor genera un ELECTRONICO vacio
     */
    public ElectronicoDTO() {
    }

    /**
     * Este constructor......
     * @param matricula matricula del tipo String con formato '1A' @type String
     * @param nombre
     * @param codigo
     * @param ffac
     * @param precio
     * @param categoria
     */
    public ElectronicoDTO(String matricula, String nombre, String codigo, String ffac, double precio, String categoria) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.codigo = codigo;
        this.ffac = ffac;
        this.precio = precio;
        this.categoria = categoria;
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

    /**
     * Este metodo retorna el codigo de barras de la instancia
     * @return @type String retorna un estring con el codigo de barras
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFfac() {
        return ffac;
    }

    public void setFfac(String ffac) {
        this.ffac = ffac;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ElectronicoDTO{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", paterno='" + codigo + '\'' +
                ", ffac='" + ffac + '\'' +
                ", estatura=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectronicoDTO electronicoDTO = (ElectronicoDTO) o;
        return Objects.equals(matricula, electronicoDTO.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

}
