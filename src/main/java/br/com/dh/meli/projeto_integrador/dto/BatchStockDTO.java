package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.BatchStock;
import lombok.*;
import javax.validation.constraints.*;
import java.time.*;

@Data
@Builder
public class BatchStockDTO {
    @NotNull
    public Integer batchNumber;

    @NotEmpty
    @Size(min = 5, max = 40)
    public String productId;

    @NotNull
    @DecimalMin(value = "-40.0", message = "O valor da temperatura precisa ser no min -40.")
    @DecimalMax(value = "30", message = "O valor da temperatura precisa ser até 30.")
    public Float currentTemperature;

    @NotNull
    @DecimalMin(value = "-40.0", message = "O valor da temperatura precisa ser no min -40.")
    @DecimalMax(value = "30", message = "O valor da temperatura precisa ser até 30.")
    public Float minimumTemperature;

    @NotNull
    public Integer initialQuantity;

    @NotNull
    public Integer currentQuantity;

    @NotNull
    public LocalDate manufacturingDate;

    @NotNull
    public LocalDateTime manufacturingTime;

    @NotNull
    public LocalDate dueDate;
}
