package operator.genericoperator;

import lexer.token.FoundToken;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GenericOperatorGroup {
    private List<Operator> operators;

    @Override
    public String toString() {
        return groupType + ", " + operators.toString();
    }

    public OperatorType getGroupType () {
        return groupType;
    }

    private OperatorType groupType;

    public GenericOperatorGroup(OperatorType groupType, Operator... operators) {
        this(groupType, Arrays.stream(operators).collect(Collectors.toList()));
    }

    public GenericOperatorGroup (OperatorType groupType, List<Operator> operators) {
        this.groupType = groupType;
        this.operators = operators;


        for (Operator op : operators) {
            if (!op.getOperatorType().equals(groupType)) {
                throw new RuntimeException("Group type is " + groupType + ", but encountered a " + op + " operator with type " + op.getOperatorType() + "!");
            }
        }
    }

    public boolean isOperator (FoundToken token) {
        return operators
                .stream()
                .anyMatch(operator -> token.is(operator.getTokenType()));
    }

    public Operator getOperator (FoundToken token) {
        return operators
                .stream()
                .filter(operator -> token.is(operator.getTokenType()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("OldToken " + token + " is not defined as an operator!"));
    }
}
