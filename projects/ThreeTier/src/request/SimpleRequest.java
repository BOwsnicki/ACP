package request;

// (0#song#mood:angry,artist:Pink)
// (1#song#mood:angry,artist:Body Count,title:Institutionalized)
// (2#song#mood:happy)

public class SimpleRequest {
	public static final int GET = 0;
	public static final int POST = 1;
	public static final int DELETE = 2;
	
	// Factory method!
	public static SimpleRequest fromString(String rep) {
		return new SimpleRequest(rep);
	}
	
	private int method;
	String resource;
	private ArgMap arg;
	
	private ArgMap makeMap(String assocString) {
		ArgMap result = new ArgMap();
		String[] assocs = assocString.split(",");
		for (int i = 0; i < assocs.length; i++) {
			String[] keyValue = assocs[i].split(":");
			result.add(keyValue[0], keyValue[1]);
		}
		return result;
	}
	
	public SimpleRequest(int method, String resource, String arg) {
		this.method = method;
		this.resource = resource;
		this.arg = makeMap(arg);
	}
	
	public SimpleRequest(String rep) {
		// Strip parentheses
		String stripped = rep.substring(1,rep.length()-1);
		String[] split = stripped.split("#");
		method = Integer.parseInt(split[0]);
		resource = split[1];
		arg = makeMap(split[2]);
	}

	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public ArgMap getArgMap() {
		return arg;
	}
	public void setArgMap(ArgMap arg) {
		this.arg = arg;
	}
	
	@Override
	public String toString() {
		return "(" + method + "#" + resource + "#" + arg + ")";
	}
	
	public static void main(String[] args) {
		SimpleRequest sr = new SimpleRequest(SimpleRequest.GET,"song","mood:angry,artist:cher");
		String srep = sr.toString();
		System.out.println(srep);
		SimpleRequest sr2 = SimpleRequest.fromString(srep);
		System.out.println(sr2);
	}
}
