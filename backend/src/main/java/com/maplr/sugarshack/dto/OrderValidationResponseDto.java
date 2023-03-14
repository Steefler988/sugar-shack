package com.maplr.sugarshack.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderValidationResponseDto {
    private boolean isOrderValid;
    private List<String> errors = new ArrayList<>();
}

