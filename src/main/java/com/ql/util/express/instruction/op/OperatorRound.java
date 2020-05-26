package com.ql.util.express.instruction.op;

import com.ql.util.express.Operator;
import com.ql.util.express.OperatorOfNumber;

public class OperatorRound extends Operator {
	public OperatorRound(String name) {
		this.name = name;
	}

	public Object executeInner(Object[] list) throws Exception {
		return executeInner(list[0], list[1]);
	}

	public Object executeInner(Object op1,Object op2) throws Exception {
		double r = OperatorOfNumber.round(
				((Number) op1).doubleValue(), ((Number) op2).intValue());
		return new Double(r);
	}
}
