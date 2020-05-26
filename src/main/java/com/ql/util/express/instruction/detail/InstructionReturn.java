package com.ql.util.express.instruction.detail;

import java.util.List;

import com.ql.util.express.RunEnvironment;

public class InstructionReturn extends Instruction{
	private static final long serialVersionUID = -4991998239277488949L;
	boolean haveReturnValue;
	public InstructionReturn(boolean aHaveReturnValue){
		this.haveReturnValue = aHaveReturnValue;
	}
	public void execute(RunEnvironment environment,List<String> errorList)throws Exception{
		//目前的模式，不需要执行任何操作
		if(environment.isTrace() && log.isDebugEnabled()){
			log.debug(this);
		}
		if(this.haveReturnValue == true){			
		   environment.quitExpress(environment.pop().getObject(environment.getContext()));
		}else{
		   environment.quitExpress();
		}
		environment.gotoLastWhenReturn();
	}
	public String toString(){
		if(this.haveReturnValue){
	         return "return [value]";	
		}else{
			return "return";
		}
	}	
}
