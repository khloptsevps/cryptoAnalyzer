package model.actions;

import model.dto.CipherRequest;

public class Encrypt implements Action {
    @Override
    public void execute(CipherRequest request) {
        System.out.println("Шифруем...");
    }
}
