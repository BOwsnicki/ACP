package response;

import request.SimpleRequest;

public class SimpleResponse {
	public static final int STATUS_OK = 200;
	public static final int STATUS_BAD_REQUEST = 400;
	public static final int STATUS_NOTFOUND = 404;
	
	// Factory method!
	public static SimpleResponse fromString(String rep) {
		return new SimpleResponse(rep);
	}
	
	private int status;
	private String body;
	
	public SimpleResponse(int status, String body) {
		super();
		this.status = status;
		this.body = body;
	}
	
	public SimpleResponse(String rep) {
		// Strip parentheses
		String stripped = rep.substring(1,rep.length()-1);
		String[] split = stripped.split("#");
		status = Integer.parseInt(split[0]);
		body = split[1];
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "(" + status + "#" + body + ")";
	}
	
	public static void main(String[] args) {
		SimpleResponse sr = new SimpleResponse(SimpleResponse.STATUS_OK,"1");
		// SimpleResponse sr = new SimpleResponse(SimpleResponse.STATUS_OK,"{\"title\":\"Good Life\",\"artist\":\"One Republic\",\"mood\":\"happy\"}");
		String srep = sr.toString();
		System.out.println(srep);
		SimpleResponse sr2 = SimpleResponse.fromString(srep);
		System.out.println(sr2);
	}
}
