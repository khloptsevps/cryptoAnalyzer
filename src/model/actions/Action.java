package model.actions;

import model.context.CipherContext;

public interface Action {
    void execute(CipherContext context);
    default String successMessage() {
        return "\uD83D\uDD24 Операция успешно выполнена!";
    }
}
