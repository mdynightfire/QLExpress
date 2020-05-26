package com.ql.util.express.test;

import com.ql.util.express.Operator;
import com.ql.util.express.OperatorOfNumber;


class GroupOperator extends Operator {
	public GroupOperator(String aName) {
		this.name= aName;
	}
	public Object executeInner(Object[] list)throws Exception {
		Object result = Integer.valueOf(0);
		for (int i = 0; i < list.length; i++) {
			result = OperatorOfNumber.add(result, list[i],false);
		}
		return result;
	}
}

