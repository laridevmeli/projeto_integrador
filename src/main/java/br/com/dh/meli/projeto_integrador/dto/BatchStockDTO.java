package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.enums.State;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import lombok.*;
import javax.validation.constraints.*;
import java.time.*;

@Data
@Builder
public class BatchStockDTO {
    @NotNull
    private Integer batchNumber;

    @NotEmpty
    @Size(min = 5, max = 40)
    private String productId;

    @NotNull
    @DecimalMin(value = "-40.0", message = "O valor da temperatura precisa ser no min -40.")
    @DecimalMax(value = "30", message = "O valor da temperatura precisa ser até 30.")
    private Float currentTemperature;

    @NotNull
    @DecimalMin(value = "-40.0", message = "O valor da temperatura precisa ser no min -40.")
    @DecimalMax(value = "30", message = "O valor da temperatura precisa ser até 30.")
    private Float minimumTemperature;

    @NotNull
    private Integer initialQuantity;

    @NotNull
    private Integer currentQuantity;

    @NotNull
    private LocalDate manufacturingDate;

    @NotNull
    private LocalDateTime manufacturingTime;

    @NotNull
    private LocalDate dueDate;
    private State state;

}
