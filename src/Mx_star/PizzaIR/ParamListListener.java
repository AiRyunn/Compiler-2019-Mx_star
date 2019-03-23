package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ParamListListener extends Mx_starBaseListener {
    Params params;

    @Override
    public void enterParamList(Mx_starParser.ParamListContext ctx) {
        params = new Params();

        ctx.object().forEach(member -> {
            ObjectListener lser = new ObjectListener();
            member.enterRule(lser);
            String type = lser.type;
            params.add(type);
        });
    }
}