package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.enums.State;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * BatchStock Test Util
 *
 * @author Lucas Pinheiro Rocha
 * @author Alexandre Borges Souza
 * @author Evelyn Cristini
 * @since 15/08/2022
 */
public class BatchStocksTestUtil {

    /**
     * Generate empty BatchStockDTO
     *
     * @return BatchStockDTO
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static BatchStockDTO generatorDTO() {
        BatchStockDTO dto = BatchStockDTO.builder().build();
        return dto;
    }

    /**
     * Method that helps to create a static batchStock object to be used on Inbound Order payload
     *
     * @return returns a static batch stock
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     */
    public static BatchStockDTO batchStockDTOSampleOne () {
        LocalDate now = LocalDate.of(2022, 05, 1);
        LocalDate dueDate = LocalDate.of(2025, 12, 1);
        BatchStockDTO dto = generatorDTO();
        dto.setBatchNumber(10);
        dto.setProductId("Teste 1");
        dto.setInitialQuantity(7);
        dto.setCurrentQuantity(7);
        dto.setMinimumTemperature(0F);
        dto.setCurrentTemperature(30F);
        dto.setManufacturingDate(now);
        dto.getManufacturingTime();
        dto.setDueDate(dueDate);
        return dto;
    }

    /**
     * Return BatchStock by BatchStockDTO
     *
     * @return BatchStock
     * @author Alexandre Borges Souza
     */
    public static BatchStock batchStockSampleOneByDTO() {
        BatchStock batchStock = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(batchStockDTOSampleOne());
        return batchStock;
    }

    /**
     * Generate list of BatchStockDTO for payload
     *
     * @return List<BatchStockDTO>
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static List<BatchStockDTO> payloadForInboundOrderPayload() {
        List<BatchStockDTO> list = new ArrayList<>();
        list.add(batchStockDTOSampleOne());
        list.add(batchStockDTOSampleTwo());
        return list;
    }



    /**
     * Generate list of BatchStockDTO
     *
     * @return List<BatchStockDTO>
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static List<BatchStock> listOfBatchStock() {
        List<BatchStockDTO> batchStockList = payloadForInboundOrderPayload();
        List<BatchStock> mapped = IBatchStockMapper.MAPPER.mapDTO(batchStockList);
        mapped.stream().forEach(model -> {
            model.setInboundOrder(InboundOrderUtil.emptyInboundOrder());
            model.setSection(SectionUtil.sectionGenerator(WarehouseUtil.warehouseGenerator(), mapped));
        });
        return mapped;
    }

    public static BatchStockDTO batchStockDTOSampleTwo () {
        LocalDate now = LocalDate.of(2022, 05, 1);
        LocalDate dueDate = LocalDate.of(2021, 12, 1);
        BatchStockDTO dto = generatorDTO();
        dto.setBatchNumber(10);
        dto.setProductId("Teste 2");
        dto.setInitialQuantity(7);
        dto.setCurrentQuantity(7);
        dto.setMinimumTemperature(0F);
        dto.setCurrentTemperature(30F);
        dto.setManufacturingDate(now);
        dto.getManufacturingTime();
        dto.setDueDate(dueDate);
        return dto;
    }


}
