package com.ql.util.express.instruction.op;

import com.ql.util.express.ArraySwap;
import com.ql.util.express.ExpressUtil;
import com.ql.util.express.InstructionSetContext;
import com.ql.util.express.OperateData;
import com.ql.util.express.instruction.OperateDataCacheManager;

public class OperatorCast extends OperatorBase {
	public OperatorCast(String aName) {
		this.name = aName;
	}

	public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
		Class<?> tmpClass = (Class<?>) list.get(0).getObject(parent);
		Object castObj = ExpressUtil.castObject(list.get(1).getObject(parent), tmpClass,true);
		OperateData result = OperateDataCacheManager.fetchOperateData(castObj,tmpClass);
		return result;
	}
}
