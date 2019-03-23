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

        // data.add("func", PizzaIR.varList.toJson());
        /*
         * inst = new JsonObject();
         * 
         * Instructions variables = new Instructions(); JsonArray classes = new
         * JsonArray(), functions = new JsonArray();
         * 
         * 
         * ctx.variableDefinitionStatement().forEach(sub -> { var lser = new
         * VariableDefinitionStatementListener(); sub.enterRule(lser);
         * variables.concat(lser.insts); }); ctx.classDefinitionStatement().forEach(sub
         * -> { var lser = new ClassDefinitionStatementListener(); sub.enterRule(lser);
         * }); ctx.functionDefinitionStatement().forEach(sub -> { var lser = new
         * FunctionDefinitionStatementListener(); sub.enterRule(lser); });
         * 
         * // inst.add("variables", variables.toJson()); inst.add("variables",
         * PizzaIR.varList.toJson()); inst.add("classes", classes);
         * inst.add("functions", functions);
         */
    }

}