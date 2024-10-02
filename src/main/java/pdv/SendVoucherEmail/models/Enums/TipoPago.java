package pdv.SendVoucherEmail.models.Enums;

import com.google.gson.annotations.SerializedName;

import static java.util.Arrays.stream;

public enum TipoPago {
    @SerializedName("0")
    Transferencia_DepositoBancario(0),
    @SerializedName("1")
    Tarjeta(1),
    @SerializedName("3")
    Efectivo(3);

    private final int value;

    TipoPago(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TipoPago fromCode(Integer code) {
        return stream(values()).filter(e -> e.value == code)
                .findFirst()
                .orElse(Efectivo);
    }
}