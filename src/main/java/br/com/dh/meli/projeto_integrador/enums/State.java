package br.com.dh.meli.projeto_integrador.enums;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public enum State {
    OK,
    A_VENCER,
    VENCIDO;


    public static State setState(LocalDate date){
        long differenceData = DAYS.between(LocalDate.now(), date);
        if (differenceData <0) {
            return State.values()[2];
        }
        if (differenceData >= 0 && differenceData <= 21){
            return State.values()[1];
        }
        return State.values()[0];
    }

    public static State valueOf(int StateId) {
        if (StateId > 2 || StateId < 0) {
            throw new BadRequestException("invalid State");
        }
        return State.values()[StateId];
    }

    public static State getEnumName(String name) {
        try {
            return State.valueOf(name.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestException("Invalid State");
        }
    }
}
