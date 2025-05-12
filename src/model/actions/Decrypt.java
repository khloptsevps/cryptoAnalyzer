package model.actions;

import model.context.CipherContext;

public class Decrypt implements Action{
    @Override
    public void execute(CipherContext context) {
        System.out.println("Расшифровываем...");
    }
}
