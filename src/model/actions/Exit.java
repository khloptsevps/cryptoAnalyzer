package model.actions;

import model.context.CipherContext;

public class Exit implements Action{
    @Override
    public void execute(CipherContext context) {
        System.exit(0);
    }
}
