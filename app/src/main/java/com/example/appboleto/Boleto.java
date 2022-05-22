package com.example.appboleto;

public class Boleto {
    private int id;
    private String cliente;
    private float precio;
    private int tipo;
    private String fecha;
    private String destino;

    // Constructor vacío
    public Boleto(){
        this.id = 0;
        this.cliente = "";
        this.precio = 0.0f;
        this.tipo = 0;
        this.fecha = "";
        this.destino = "";
    }

    //Constructor de parámetros
    public Boleto(int id, String cliente, float precio, int tipo, String fecha, String destino){
        this.id = id;
        this.cliente = cliente;
        this.precio = precio;
        this.tipo = tipo;
        this.fecha = fecha;
        this.destino = destino;
    }

    // Constructor de copia
    public Boleto(Boleto bol){
        this.id = bol.id;
        this.cliente = bol.cliente;
        this.precio = bol.precio;
        this.tipo = bol.tipo;
        this.fecha = bol.fecha;
        this.destino = bol.destino;
    }

    //Métodos de propiedades | Encapsulamiento

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    // Métodos de comportamiento

    public float calcularSubtotal(){
        float subtotal = 0.0f;
        if (this.tipo == 1){
            subtotal = this.precio;
        }else if (this.tipo == 2){
            subtotal = this.precio * 1.8f;
        }
        return subtotal;
    }

    public float calcularImpuesto(){
        float impuesto = calcularSubtotal() * 0.16f;
        return impuesto;
    }

    public float hacerDescuento(int edad){
        float descuento = 0.0f;
        if (edad >= 60) {
            descuento = this.precio / 2;
        } else {
            descuento = 0;
        }
        return descuento;
    }

    public String calcularTotal(int edad){
        String totalS = "";
        float total = calcularSubtotal() + calcularImpuesto() - hacerDescuento(edad);

        totalS = "SUBTOTAL: $" + calcularSubtotal() + "\n" +
                "IMPUESTO: $" + calcularImpuesto() + "\n" +
                "DESCUENTO: $" + hacerDescuento(edad) + "\n\n" +
                "TOTAL A PAGAR: $" + total + "\n" +
                "--------------------------------------------";

        return totalS;
    }

    public String imprimirBoleto(int edad){
        String costoTotal = "";
        String descTipo = "";
        if (this.tipo == 1){
            descTipo = "Sencillo";
        } else if (this.tipo == 2){
            descTipo = "Doble";
        }

        costoTotal = "--------------------------------------------" + "\n" +
                "EL DESTINO FELIZ -- AGENCIA DE VIAJES \n\n" +
                "No. BOLETO: " + this.id + "\n" +
                "FECHA: " + this.fecha + "\n" +
                "NOMBRE DEL CLIENTE: " + this.cliente + "\n" +
                "DESTINO: " + this.destino + "\n" +
                "TIPO DE VIAJE: " + this.tipo + " - " + descTipo + "\n" +
                "PRECIO: $" + this.precio + "\n\n" +
                "------ IMPORTE ------" + "\n";

        return costoTotal + calcularTotal(edad);
    }
}
