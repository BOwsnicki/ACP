package response;

public class SimpleResponse {
	public static final int STATUS_OK = 200;
	public static final int STATUS_BAD_REQUEST = 400;
	public static final int STATUS_NOTFOUND = 404;
	
	// Factory method!
	public static SimpleResponse fromString(String jsonString) {
		return J2Response.fromJSONString(jsonString);
	}
	
	private int status;
	private String body;
	
	public SimpleResponse(int status, String body) {
		super();
		this.status = status;
		this.body = body;
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
		return J2Response.toJSONString(this);
	}
}
