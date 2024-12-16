package mx.unam.dgtic.system.exception;


import java.time.LocalDateTime;

public class DetalleError {

    private String statusCode;
    private String message;
    private String detalle;
    private LocalDateTime timeStamp;

    public DetalleError() {
    }

    public DetalleError(String statusCode, String message, String detalle, LocalDateTime timeStamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.detalle = detalle;
        this.timeStamp = timeStamp;
    }

    public String getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
