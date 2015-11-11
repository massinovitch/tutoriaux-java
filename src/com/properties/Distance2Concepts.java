package com.properties;

public class Distance2Concepts {
	private int positionConceptInListAmbigu; // la position du concept retenu dans la liste des concept ambigue
	private int index1;//position de la distance du concept 1
	private int index2;//position de la distance du concept 2
	private float value;//valeur de la distance du concept correspondant
		
	public int getPositionConceptInListAmbigu() {
		return positionConceptInListAmbigu;
	}
	public void setPositionConceptInListAmbigu(int positionConceptInListAmbigu) {
		this.positionConceptInListAmbigu = positionConceptInListAmbigu;
	}	
	public int getIndex1() {
		return index1;
	}
	public void setIndex1(int index1) {
		this.index1 = index1;
	}
	public int getIndex2() {
		return index2;
	}
	public void setIndex2(int index2) {
		this.index2 = index2;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
}
