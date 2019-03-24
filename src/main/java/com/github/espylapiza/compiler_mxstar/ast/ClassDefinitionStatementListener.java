package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.ClassMemberContext;

class ClassDefinitionStatementListener extends Mx_starBaseListener {
    @Override
    public void enterClassDefinitionStatement(Mx_starParser.ClassDefinitionStatementContext ctx) {
        String classname = ctx.Identifier().getText();
        PizzaIR.dom.enterClass(classname);

        switch (PizzaIR.state) {
        case TYPE_DECLARATION:
            PizzaIR.typeList.addType(new Type(PizzaIR.dom.getClassTrace()));
            // TODO
            break;
        case MEMBER_DECLARATION:
        case TRANSLATION:
            for (ClassMemberContext member : ctx.classMember()) {
                ClassMemberListener lser = new ClassMemberListener();
                member.enterRule(lser);
            }
            break;
        }

        PizzaIR.dom.exitClass();
    }
}