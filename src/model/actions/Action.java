package model.actions;

import model.dto.CipherRequest;

public interface Action {
    void execute(CipherRequest request);
}
