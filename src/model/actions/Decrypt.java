package model.actions;

import model.alphabet.Alphabet;
import dto.CipherRequest;

public class Decrypt implements Action{
    @Override
    public void execute(CipherRequest request, Alphabet alphabet) {
        System.out.println("Расшифровываем...");
    }
}
