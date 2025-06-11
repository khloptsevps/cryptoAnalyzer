package org.khloptsev.cryptoanalyzer.model.actions;

import org.khloptsev.cryptoanalyzer.model.context.CipherContext;

public class Exit implements Action{
    @Override
    public void execute(CipherContext context) {
        System.exit(0);
    }
}
