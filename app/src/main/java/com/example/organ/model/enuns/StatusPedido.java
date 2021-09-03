package com.example.organ.model.enuns;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    ENVIADO(3),
    ENTREGUE(4),
    CANCELADO(5);
    private int code;

    StatusPedido(int code) {
    }

    public int getCode() {
        return code;
    }

    public static StatusPedido valueOf(int code) throws IllegalAccessException {
        for (StatusPedido valor : StatusPedido.values()) {
            if (valor.code == code) {
                return valor;
            }
        }
        throw new IllegalAccessException("Invalido StatusPedido code");

    }

}


