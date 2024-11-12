package com.alucontrol.saleservice.exceptions;

import java.util.UUID;

// This is a "record" class, it's a new way to declare an immutable class
// of simple data in Java, with the main purpose of
// reducing the amount of boilerplate code you need to write.
// The idea is that a record is a class type representing a dataset,
// focusing on providing immutability and automatic implementations of
// methods like toString(), equals(), hashCode(), and a constructor.

public record ProblemDetails(
        String status,
        String title,
        String description,
        String details,
        UUID correlationId) {
}
