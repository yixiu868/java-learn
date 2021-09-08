package com.ww.weak;

public class Apple {

    private String name;
    
    public Apple(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Apple: " + name + " finalize.");
    }

    @Override
    public String toString() {
        return "Apple [name=" + name + "]";
    }
}
