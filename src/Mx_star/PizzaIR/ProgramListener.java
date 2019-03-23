package Mx_star.PizzaIR;

import Mx_star.AST.*;
import com.google.gson.*;

public class ProgramListener extends Mx_starBaseListener {
    JsonObject data;

    @Override
    public void enterProgram(Mx_starParser.ProgramContext ctx) {
        data = new JsonObject();

        for (var programSection : ctx.programSection()) {
            var lser = new ProgramSectionListener();
            programSection.enterRule(lser);
        }

        data.add("Type", PizzaIR.typeList.toJson());
        data.add("Func", PizzaIR.funcList.toJson());
        data.add("Var", PizzaIR.varList.toJson());
    }

}