package Mx_star.PizzaIR;

import Mx_star.AST.*;

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
            for (var member : ctx.classMember()) {
                var lser = new ClassMemberListener();
                member.enterRule(lser);
            }
            break;
        }

        PizzaIR.dom.exitClass();
    }
}