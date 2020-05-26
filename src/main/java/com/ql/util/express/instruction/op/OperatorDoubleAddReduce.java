package com.ql.util.express.instruction.op;

import com.ql.util.express.ArraySwap;
import com.ql.util.express.ExpressUtil;
import com.ql.util.express.InstructionSetContext;
import com.ql.util.express.OperateData;
import com.ql.util.express.OperatorOfNumber;
import com.ql.util.express.instruction.OperateDataCacheManager;

public    class OperatorDoubleAddReduce extends OperatorBase {
	public OperatorDoubleAddReduce(String name) {
		this.name = name;
	}

	public OperateData executeInner(InstructionSetContext parent,
			ArraySwap list) throws Exception {
		Object obj = list.get(0).getObject(parent);
		Object result = null;
		if (this.getName().equals("++")) {
			result = OperatorOfNumber.add(obj, 1,this.isPrecise);
		} else if (this.getName().equals("--")) {
			result = OperatorOfNumber.subtract(obj, 1,this.isPrecise);
		}
		((OperateData)list.get(0)).setObject(parent, result);
		
		if(result == null){
			return OperateDataCacheManager.fetchOperateData(null,null);
		}else{
			return OperateDataCacheManager.fetchOperateData(result,ExpressUtil.getSimpleDataType(result.getClass()));
		}
	}
}
