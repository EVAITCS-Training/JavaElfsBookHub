package com.evaitcsmatt.bookhub.webserver.exceptions;

import java.time.LocalDateTime;

public record ErrorMessage(
		String message,
        String path,
        int status,
        LocalDateTime timestamp
		) {

}
