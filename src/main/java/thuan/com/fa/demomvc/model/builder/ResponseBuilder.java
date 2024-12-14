package thuan.com.fa.demomvc.model.builder;

public class ResponseBuilder<T> {
	private int status;
	private T result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public ResponseBuilder() {
		super();
	}

	public ResponseBuilder(Builder<T> builder) {
		this.status = builder.status;
		this.result = builder.result;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", result=" + result + "]";
	}

	public Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> {
		private int status;
		private T result;

		private Builder() {
			super();
		}

		public Builder<T> result(T result) {
			this.result = result;
			return this;
		}

		public ResponseBuilder<T> build() {
			return new ResponseBuilder<T>(this);
		}

	}

}
