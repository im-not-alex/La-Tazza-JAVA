package it.polito.latazza.exceptions;

public class DateException extends Exception {

	public DateException(String string) {
		StackTraceElement s=(Thread.currentThread().getStackTrace())[2];
		System.err.println("line "+s.getLineNumber() +"\t"+s.getMethodName()+"\t : "+string);	
	}
	private static final long serialVersionUID = 1L;

}
