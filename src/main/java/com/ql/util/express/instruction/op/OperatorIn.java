package com.ql.util.express.instruction.op;

import java.lang.reflect.Array;
import java.util.List;

import com.ql.util.express.Operator;
import com.ql.util.express.config.QLExpressRunStrategy;
import com.ql.util.express.exception.QLException;

public class OperatorIn extends Operator {
	public OperatorIn(String aName) {
		this.name = aName;
	}

	public OperatorIn(String aAliasName, String aName, String aErrorInfo) {
		this.name = aName;
		this.aliasName = aAliasName;
		this.errorInfo = aErrorInfo;
	}

	public Object executeInner(Object[] list) throws Exception {
		Object obj = list[0];
		if (obj == null) {
		    if(QLExpressRunStrategy.isAvoidNullPointer()){
                //避免空指针策略异常则返回false
		        return false;
            }
			// 对象为空，不能执行方法
			String msg = "对象为空，不能执行方法:";
			throw new QLException(msg + this.name);
		} else if (((obj instanceof Number) || (obj instanceof String)) == false) {
			String msg = "对象类型不匹配，只有数字和字符串类型才才能执行 in 操作,当前数据类型是:";
			throw new QLException(msg + obj.getClass().getName());
		} else if(list.length == 2 && (list[1].getClass().isArray() || list[1] instanceof List)){
			       if(obj.equals(list[1]) == true){
			    	   return true;
			       }else if(list[1].getClass().isArray()){
					   int len = Array.getLength(list[1]);
					   for(int i=0;i<len;i++){
						    boolean f = OperatorEqualsLessMore.executeInner("==", obj,Array.get(list[1],i));
							if (f == true) {
								return Boolean.TRUE;
							}
					   }
				   }else if(list[1] instanceof List){
					@SuppressWarnings("unchecked")
					List<Object> array = (List<Object>)list[1];
					   for(int i=0;i<array.size();i++){
						    boolean f = OperatorEqualsLessMore.executeInner("==", obj,array.get(i));
							if (f == true) {
								return Boolean.TRUE;
							}
					   }				   
				   }
				   return false;
		} else {
			for (int i = 1; i < list.length; i++) {
				boolean f = OperatorEqualsLessMore.executeInner("==", obj,list[i]);
				if (f == true) {
					return Boolean.TRUE;
				}
			}
			return Boolean.FALSE;
		}
	}

}
