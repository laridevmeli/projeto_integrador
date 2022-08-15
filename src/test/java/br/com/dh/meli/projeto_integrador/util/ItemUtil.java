package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.model.Item;

public class ItemUtil {

    /**
     * Method that helps to create a static item object order number id to be used on Item payload
     * @author Evelyn Cristini
     * @return returns a static item
     */
    public static Item emptyItem () {
        Item item = new Item();
        item.setId(1L);
        item.setQuantity(1);

        return item;
    }

    /**
     * Method that helps to create a static empty item DTO to be used on Item payload
     * @author Evelyn Cristini
     * @return returns a dto
     */
    public static ItemDTO emptyItemDTO () {
        ItemDTO dto = new ItemDTO();
        dto.setQuantity(1);
        dto.setAdvertisementId(1L);
        dto.setBatchNumber(1);

        return dto;
    }
}
