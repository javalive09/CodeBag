package com.codebag.code.mycode.interview.noxus;

public class SameHashCodeObj {

	int index;
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	public SameHashCodeObj(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "index = " + index;
	}
	
	
	
}
