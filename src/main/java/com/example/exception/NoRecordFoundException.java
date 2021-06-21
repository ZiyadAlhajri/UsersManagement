package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class NoRecordFoundException extends Exception {

    private String message;
}
