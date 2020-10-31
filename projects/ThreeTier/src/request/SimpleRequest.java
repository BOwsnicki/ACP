package request;

public class SimpleRequest {
	public static final int GET = 0;
	public static final int POST = 1;
	
	// Factory method!
	public static SimpleRequest fromString(String jsonString) {
		return J2Request.fromJSONString(jsonString);
	}
	
	private int method;
	private String arg;
	public SimpleRequest(int method, String arg) {
		this.method = method;
		this.arg = arg;
	}
	

	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public String getArg() {
		return arg;
	}
	public void setArg(String arg) {
		this.arg = arg;
	}
	
	@Override
	public String toString() {
		return J2Request.toJSONString(this);
	}
}
