package pdv.SendVoucherEmail.models;

import java.math.BigDecimal;

import pdv.SendVoucherEmail.models.Enums.TipoPago;

public class Pago {
    private TipoPago tipoPago;
    private BigDecimal monto = BigDecimal.ZERO;

    // Getters and setters...
    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
