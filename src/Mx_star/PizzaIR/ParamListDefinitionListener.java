package Mx_star.PizzaIR;

import Mx_star.AST.*;
import Mx_star.AST.Mx_starParser.VariableDeclarationContext;

public class ParamListDefinitionListener extends Mx_starBaseListener {
    ParamsInstance params;

    @Override
    public void enterParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx) {
        params = new ParamsInstance();
        for (VariableDeclarationContext member : ctx.variableDeclaration()) {
            VariableDeclaration lser = new VariableDeclaration();
            member.enterRule(lser);
            params.add(lser.name, lser.type);
        }
    }
}