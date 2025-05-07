package model.actions;

import model.alphabet.Alphabet;
import dto.CipherRequest;

public class Exit implements Action{
    @Override
    public void execute(CipherRequest request, Alphabet alphabet) {
        System.exit(0);
    }
}
