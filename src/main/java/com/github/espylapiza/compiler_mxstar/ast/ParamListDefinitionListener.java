package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.VariableDeclarationContext;

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