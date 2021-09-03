package com.example.organ.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Pagamento {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant momento;
}
