# Available Properties to set #


  * format - PNG, JPG  default JPG
  * font - Java Font Name default "Helvetica"
  * fontsize - defaults to  20
  * min-height : minimum height of the image defaults to dynamic size -1
  * min-width : minimum width of the image defaults to dynamic size -1
  * padding-x : x padding default 20
  * padding-y : Defaults dynamically to size of text/2
  * 



# Example 1 : Using Language #

```
//Optional Properties 
Properties props = new Properties(); 
props.put("format", "jpg"); 
props.put("font", "Helvetica"); 
props.put("fontsize", "28"); 
props.put("min-width", "180"); 
props.put("padding-x", "25"); 
props.put("padding-y", "25"); 
OutputStream os = new FileOutputStream("c:/captcha/text.jpg"); 
FactoryLanguageImpl inst=(FactoryLanguageImpl)Producer.forName("org.smx.captcha.impl.FactoryLanguageImpl"); 
inst.setLanguageDirectory("C:/captcha/lang"); 
inst.setLanguage("RU"); 
inst.setRange(5, 10); //Select words between 5-10 letters
Producer.render(os, inst, props);
```