package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ParamListDefinitionListener extends Mx_starBaseListener {
    ParamsInstance params;

    @Override
    public void enterParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx) {
        params = new ParamsInstance();
        for (var member : ctx.variableDeclaration()) {
            var lser = new VariableDeclaration();
            member.enterRule(lser);
            params.add(lser.name, lser.type);
        }
    }
}