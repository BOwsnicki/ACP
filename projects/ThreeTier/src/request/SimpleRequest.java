package request;

public class SimpleRequest {
	public static final int GET = 0;
	public static final int POST = 1;
	
	// Factory method!
	public static SimpleRequest fromString(String rep) {
		return new SimpleRequest(rep);
	}
	
	private int method;
	private String arg;
	public SimpleRequest(int method, String arg) {
		this.method = method;
		this.arg = arg;
	}
	
	public SimpleRequest(String rep) {
		// Strip parentheses
		String stripped = rep.substring(1,rep.length()-1);
		String[] split = stripped.split("#");
		method = Integer.parseInt(split[0]);
		arg = split[1];
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
		return "(" + method + "#" + arg + ")";
	}
	
	
	
	public static void main(String[] args) {
		SimpleRequest sr = new SimpleRequest(SimpleRequest.GET,"mood = angry");
		// SimpleRequest sr = new SimpleRequest(SimpleRequest.POST,"{\"title\":\"Good Life\",\"artist\":\"One Republic\",\"mood\":\"happy\"}");
		String srep = sr.toString();
		System.out.println(srep);
		SimpleRequest sr2 = SimpleRequest.fromString(srep);
		System.out.println(sr2);
	}
}
