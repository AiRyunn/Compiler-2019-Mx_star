package Mx_star.AST;

public class Evaluator extends Mx_starBaseListener {

    @Override
    public void enterProgram(Mx_starParser.ProgramContext ctx) {
        System.out.println("enter Program");
        System.out.println(ctx.EOF());
        System.out.println(ctx.variableDeclaration(0).getRuleIndex());
    }
}
