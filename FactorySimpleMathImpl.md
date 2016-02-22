# About #
Simple Expression Parser,currently it only supports +- for simplicy who want to solve 3+7\*23 if users request this feature, I will add full blown expression parser using RPN.

# Available Properties #
  * min - minimum number used in expression default = 1
  * max - maximum number used in expression default = 20
  * symbols - number of sumbols used to create expression

# Example #

```
	FactorySimpleMathImpl inst=(FactorySimpleMathImpl)Producer.forName("org.smx.captcha.impl.FactorySimpleMathImpl");
		
		Properties props=new Properties();
		props.setProperty("min","1");
		props.setProperty("max","5");
		props.setProperty("symbols","2");
		
		inst.setProperties(props);
		
		System.out.println("HashSolved="+inst.getHashCode(inst.getWord()));
		System.out.println("Solved For="+inst.getLastWord());
```
This will generate expressions of type: 2+4-1