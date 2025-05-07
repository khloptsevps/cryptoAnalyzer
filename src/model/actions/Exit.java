package model.actions;

import model.dto.CipherRequest;

public class Exit implements Action{
    @Override
    public void execute(CipherRequest request) {
        System.exit(0);
    }
}
