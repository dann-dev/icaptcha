package org.smx.captcha.impl;

import java.util.Random;
import java.util.Stack;


import org.smx.captcha.IWordFactory;
/**
 * Provide captchas in form of 
 * 1 + 2 
 * 1 - 2
 ** 
 * Simple Expression Parser
 * Currently it only supports +- for simplicy 
 * who want to solve 3+7*23 
 * If users request the feature, I will add full blown
 * expression parser using RPN
 * @author gbugaj
 *
 */
public class FactorySimpleMathImpl extends IWordFactory {
	
	private int current_index;
	private String query;
	private String symbols[]={"+","-"};
	public static IWordFactory getInstance() {
		if(instance==null ){
			instance=new FactorySimpleMathImpl();
		}
		return instance;
	}
	
	public String getWord(){
		word="";
		int minDigit=Integer.valueOf(getProperties().getProperty("min","1"));
		int maxDigit=Integer.valueOf(getProperties().getProperty("max","20"));
		int numberOfSymbols=Integer.valueOf(getProperties().getProperty("symbols","3"));
		Random rnd=new Random();		
		String lastSymbol="";
		//Can't have min>max
		if(minDigit>maxDigit)
		{
			maxDigit=minDigit;
			minDigit=maxDigit;
		}
		while(numberOfSymbols>0){
			String symbol=symbols[rnd.nextInt(symbols.length)];			
			int leftDig=1;			
			do{
				leftDig=rnd.nextInt(maxDigit);
				}while(leftDig<minDigit);
			
			int rightDig=1;			
			do{
				rightDig=rnd.nextInt(maxDigit);
			}while(rightDig<minDigit);
			
			word+= lastSymbol+leftDig+symbol+rightDig;
			lastSymbol=symbol;
			numberOfSymbols-=2;
		}
		return word;
	}	
	
	
	private char read_ch() {
		if (current_index == query.length())
			return 0;
		char ch = query.charAt(current_index);
		current_index++;
		return ch;
	}
	
	private void put_back() {
		current_index--;
	}
	
	
	public String getHashCode(String str){ 
		if(str==null){
			str="";
		}
		query=str;
		Stack<String> opstack=new Stack<String>();
		//Parse expresion into tokens [numbers,operands]		
		char ch;
		while ((ch = read_ch()) != 0) {
			String token = "";
			switch (ch) {
			case '+': {				
				opstack.push(ch+"");
				break;
			}
			case '-': {
				opstack.push(ch+"");
				break;
			}	
			default:
			   do{
					token += ch;
					ch = read_ch();
				} while (Character.isDigit(ch));			 
			 opstack.add(token);
			 if(current_index<query.length())
				 put_back();
		
			 break;
			}		
		}	
		int left=0;
		String oper="";
		int right=0;
		
		opstack=reverse(opstack);
		 while(!opstack.isEmpty()){		
			oper=opstack.pop();
			if(isSymbol(oper)){				
				right=Integer.valueOf(opstack.pop());			
				char sign=oper.charAt(0);
				switch (sign) {
					case '+': {				
						left=left+right;
						break;
					}
					case '-': {
						left=left-right;
						break;
					}
					
				}
			opstack.push(left+"");
		}else{
			left=Integer.valueOf(oper);
		}
			
	}
		return ""+left; 
	}
	
	//Helper method
	public <T> Stack<T> reverse(Stack<T> in) {
	      Stack<T> out = new Stack<T>();
	      while (!in.empty()) {
	        T elt = in.pop();
	        out.push(elt);
	      }
	      return out;
	}

	
	
	private boolean isSymbol(String s) {	
		for(int i=0;i<symbols.length;i++){
			if( s.equals(symbols[i])){
				return true;
			}
		}
		return false;
	}
	
}
