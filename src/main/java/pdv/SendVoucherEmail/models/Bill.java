package pdv.SendVoucherEmail.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import pdv.SendVoucherEmail.models.Enums.TipoPago;

public class Bill {
    private static final BigDecimal IVA = new BigDecimal("0.13");
    
    private BigDecimal total;
    private long consecutive;
    private Instant date;
    private List<Product> products;
    private BigDecimal tax;
    private ElectronicBill electronicBill;
    private String condicionVenta;
    private String plazoCredito;
    private BigDecimal descuento;
    @SerializedName(value = "MedioPago")
    private List<TipoPago> medioPago;
    private int numeroVoucher;
    private BigDecimal efectivo;

    public void setTotal(BigDecimal total) {
        this.tax = total.multiply(IVA);
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public long getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(long consecutive) {
        this.consecutive = consecutive;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public ElectronicBill getElectronicBill() {
        return electronicBill;
    }

    public void setElectronicBill(ElectronicBill electronicBill) {
        this.electronicBill = electronicBill;
    }

    public String getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(String condicionVenta) {
        this.condicionVenta = condicionVenta;
    }

    public String getPlazoCredito() {
        return plazoCredito;
    }

    public void setPlazoCredito(String plazoCredito) {
        this.plazoCredito = plazoCredito;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public List<TipoPago> getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(List<TipoPago> medioPago) {
        this.medioPago = medioPago;
    }

    public int getNumeroVoucher() {
        return numeroVoucher;
    }

    public void setNumeroVoucher(int numeroVoucher) {
        this.numeroVoucher = numeroVoucher;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    @Override
    public String toString() {
        return String.format("Factura No. %d del %s con un total de %s", consecutive, date.toString(),
                total.toString());
    }
}