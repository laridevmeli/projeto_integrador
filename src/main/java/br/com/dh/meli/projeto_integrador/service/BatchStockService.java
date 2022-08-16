package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.enums.State;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BatchStockService implements IBatchStockService {

    @Autowired
    private IBatchStockRepository repo;

    public List<BatchStock> batchStockMapper(List<BatchStockDTO> batches, InboundOrder inboundOrder) {
        return batches.stream().map(dto -> {
            BatchStock batch = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(dto);
            batch.setInboundOrder(inboundOrder);
            batch.setSection(inboundOrder.getSection());
            return batch;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BatchStock> saveAll(List<BatchStock> batches) {
        batches.forEach( b -> {
            b.setState(State.setState(b.getDueDate()));
        });
        return repo.saveAll(batches);
    }

    private void setState(BatchStock batchStock){
        State.setState(batchStock.getDueDate());
    }
    public List<BatchStock> updateBatchStocksDueDate(){
        List<BatchStock> batches = repo.findAll();
        if(batches.isEmpty()){
            throw new NotFoundException("Not found stocks");
        }
        return saveAll(batches);
    }

    @Override
    public void deleteBatchStocksExpired(){
        repo.deleteAll(findAllByState(State.VENCIDO));
    }



    @Override
    public List<BatchStock> findAllByState(State state) {
        List<BatchStock> batches = repo.findAllByState(state);
        if(batches.isEmpty()){
            throw new NotFoundException("Not found stocks");
        }
        return batches;
    }


    @Override
    public BatchStock findByProductId(String productId) {
        Optional<BatchStock> batchStock = repo.findBatchStockByProductId(productId);
        if(batchStock.isEmpty()) {
            throw new NotFoundException("productId not found");
        }
        return batchStock.get();
    }

    @Override
    public BatchStock findByBatchNumber(Integer batchNumber) {
        Optional<BatchStock> batchFound = repo.findBatchStockByBatchNumber(batchNumber);
        if (batchFound.isEmpty()) {
            throw new PreconditionFailedException("batch doesn't exists");
        }
        return batchFound.get();
    }

    @Override
    public void batchNumberExistenceValidation(Integer batchNumber) {
        Optional<BatchStock> batchFound = repo.findBatchStockByBatchNumber(batchNumber);
        if (batchFound.isPresent()) {
            throw new PreconditionFailedException("batch already exists");
        }
    }

    @Override
    public List<CountStocks> countStocksByProductId(String productId) {
         if(!repo.existsBatchStocksByProductId(productId))
         {
             throw new NotFoundException("productId not found");
         }
         return repo.countStocksByProductId(productId);
    }

    @Override
    public List<BatchStock> findAllByProductId(String productId) {
        return repo.findBatchStocksByProductId(productId);
    }

    @Override
    public List<BatchStockDTO> toDTOs(List<BatchStock> batches) {
        return IBatchStockMapper.MAPPER.map(batches);
    }

    @Override
    public List<BatchStock> findAllBySectionsOrderByDueDate(List<Section> sections) {
        List<BatchStock> list = new ArrayList<>();
        sections.forEach(s -> list.addAll(repo.findBatchStocksBySectionOrderByDueDateAsc(s)));
        if (list.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return list;
    }

    @Override
    public List<BatchStock> findAllBySectionsAndByDueDateLessThan(List<Section> sections, LocalDate limitDate) {
        List<BatchStock> list = new ArrayList<>();
        sections.forEach(s -> list.addAll(repo.findBatchStocksBySectionAndDueDateLessThanOrderByDueDateAsc(s, limitDate)));
        if (list.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return list;
    }

    @Override
    public BatchStock decreaseQuantity(BatchStock batchStock, Integer quantity) {
        batchStock.setCurrentQuantity(batchStock.getCurrentQuantity() - quantity);
        return repo.save(batchStock);
    }
}
