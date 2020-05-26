package com.ql.util.express.example.operator;

import com.ql.util.express.Operator;

public class AddTwiceOperator extends Operator{

	public Object executeInner(Object[] list) throws Exception {
		int a = (Integer)list[0];
		int b = (Integer)list[1];
		return a + b + b;
	}

}
