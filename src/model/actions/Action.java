package model.actions;


import model.context.CipherContext;

public interface Action {
    void execute(CipherContext context);
}
