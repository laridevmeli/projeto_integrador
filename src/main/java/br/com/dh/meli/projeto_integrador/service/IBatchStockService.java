package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.enums.State;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Section;

import java.time.LocalDate;
import java.util.*;

public interface IBatchStockService {
    List<BatchStock> batchStockMapper(List<BatchStockDTO> batches, InboundOrder inboundOrder);
    List<BatchStock> saveAll(List<BatchStock> batches);
    BatchStock findByProductId(String productId);
    BatchStock findByBatchNumber(Integer batchNumber);
    void batchNumberExistenceValidation(Integer batchNumber);
    List<CountStocks> countStocksByProductId(String productId);
    List<BatchStock> findAllByProductId(String productId);
    BatchStock decreaseQuantity(BatchStock batchStock,Integer quantity);
    List<BatchStock> findAllBySectionsOrderByDueDate(List<Section> sections);
    List<BatchStock> findAllBySectionsAndByDueDateLessThan(List<Section> sections, LocalDate limitDate);
    List<BatchStockDTO> toDTOs(List<BatchStock> batches);
    List<BatchStock> updateBatchStocksDueDate();
    void deleteBatchStocksExpired();
    List<BatchStock> findAllByState(State state);
}
