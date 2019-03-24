package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.ClassMemberContext;

class ClassDefinitionStatementListener extends Mx_starBaseListener {
    @Override
    public void enterClassDefinitionStatement(Mx_starParser.ClassDefinitionStatementContext ctx) {
        String classname = ctx.Identifier().getText();
        PizzaIRBuilder.dom.enterClass(classname);

        switch (PizzaIRBuilder.state) {
        case TYPE_DECLARATION:
            PizzaIRBuilder.typeList.addType(new Type(PizzaIRBuilder.dom.getClassTrace()));
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

        PizzaIRBuilder.dom.exitClass();
    }
}