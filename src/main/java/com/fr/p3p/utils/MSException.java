package com.fr.p3p.utils;

public class MSException extends RuntimeException{

		private static final long serialVersionUID = 1L;
		private Integer errorCode;
		private Boolean status = false;
		private String message;

		public MSException(String message) {
			super(message);
			this.message = message;
		}

		public MSException(Integer error, String message) {
			super(message);
			this.errorCode = error;
			this.message = message;
		}

		public MSException(Integer error, Exception ex) {
			super(ex);
			this.errorCode = error;
			this.message = ex.getMessage();
		}

		public MSException(Exception ex) {
			super(ex);
			this.message = ex.getMessage();
		}

		public MSException(ErrorCode errorCode, String message) {
			super(message);
			this.errorCode = errorCode.getCode();
			this.message = message;
		}

		@Override
		public String getMessage() {
			return message;
		}

		public Integer getErrorCode() {
			return errorCode;
		}

}
