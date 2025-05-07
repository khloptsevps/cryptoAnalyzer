package model.actions;

import model.alphabet.Alphabet;
import dto.CipherRequest;

public interface Action {
    void execute(CipherRequest request, Alphabet alphabet);
}
