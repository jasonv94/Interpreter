
public class Variable {
private int value;
private boolean initialized;

public Variable() {
	initialized=false;
}


public int getValue() throws UndefinedVariableException {
	if(!initialized) {
	throw (new UndefinedVariableException());
	}
	return value;
}
public void setValue(int value) {
	this.value = value;
	initialized =true;
}
public boolean isInitialized() {
	return initialized;
}


}
