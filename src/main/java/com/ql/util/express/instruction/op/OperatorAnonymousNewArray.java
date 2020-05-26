package com.ql.util.express.instruction.op;

import java.lang.reflect.Array;

import com.ql.util.express.ArraySwap;
import com.ql.util.express.ExpressUtil;
import com.ql.util.express.InstructionSetContext;
import com.ql.util.express.OperateData;
import com.ql.util.express.instruction.OperateDataCacheManager;

public class OperatorAnonymousNewArray extends OperatorBase {
	public OperatorAnonymousNewArray(String aName) {
		this.name = aName;
	}
	public OperatorAnonymousNewArray(String aAliasName, String aName, String aErrorInfo) {
		this.name = aName;
		this.aliasName = aAliasName;
		this.errorInfo = aErrorInfo;
	}

	public OperateData executeInner(InstructionSetContext  context, ArraySwap list) throws Exception {

		Class<?> type = this.findArrayClassType(context,list);
		type = ExpressUtil.getSimpleDataType(type);
		int[] dims = new int[1];
		dims[0]= list.length;
		Object data = Array.newInstance(type,dims);
		for(int i=0;i<list.length;i++){
			Array.set(data, i, list.get(i).getObject(context));
		}
		return OperateDataCacheManager.fetchOperateData(data,data.getClass());
	}

	private Class<?>findArrayClassType(InstructionSetContext context, ArraySwap list) throws Exception {
		Class<?> type = null;
		for(int i=0;i<list.length;i++){
			Class<?> type1 = list.get(i).getType(context);
			if(type1==null){
				//doNothing
			}else if(type==null){
				//第一次赋值
				type = type1;
			}else if(type1==type || type.isAssignableFrom(type1)){//type1是type的子类
				//doNothing
			}else if(type1.isAssignableFrom(type)){
				//寻找更基础的类
				type = type1 ;
			}else{
				type = Object.class;
			}
		}
		if(type==null) type = Object.class;//参数全部为null的情况
		return type;
	}
}
