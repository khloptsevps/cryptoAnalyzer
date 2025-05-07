package model.actions;

import model.dto.CipherRequest;

public class Decrypt implements Action{
    @Override
    public void execute(CipherRequest request) {
        System.out.println("Расшифровываем...");
    }
}
