package com.ql.util.express.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.InstructionSet;
import com.ql.util.express.InstructionSetRunner;

public class DongtaiFieldTest {

	private static final Log log = LogFactory.getLog(DongtaiFieldTest.class);
	@Test
	public void testField() throws Exception{
		String express ="String 用户 = \"张三\";" +
				"费用.用户  = 100;" +
				"用户 = \"李四\";" +
				"费用.用户  = 200;";
		
		ExpressRunner runner = new ExpressRunner(false,true);
		DefaultContext<String, Object> context = new DefaultContext<String, Object>();
		Map<String,Object> fee = new HashMap<String,Object>();
		context.put("费用",fee);
		InstructionSet set = runner.parseInstructionSet(express);
		InstructionSetRunner.executeOuter(runner,set,null, context, null, true, false,null, true);
		runner.execute(express, context, null, false, true);
		System.out.println(context.get("费用"));
		Assert.assertTrue("动态属性错误",fee.get("张三").toString().equals("100"));
		Assert.assertTrue("动态属性错误",fee.get("李四").toString().equals("200"));
	}
	@Test
	public void testLoadFromFile() throws Exception{	
		ExpressRunner runner = new ExpressRunner(true,true);
		runner.loadExpress("TestFunctionParamerType");
		DefaultContext<String, Object>  context = new DefaultContext<String, Object>();	
		context.put("auctionUtil",new com.ql.util.express.test.BeanExample());
		context.put("log",log);
		Object r = runner.executeByExpressName("TestFunctionParamerType", context, null, false,false,null);
		System.out.println(r );
		System.out.println(context);
	}	
}
