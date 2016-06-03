package com.quant.web.response;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * API统一返回值类
 * 对应以下Json字符串格式
 * {
 *    "meta":
 *    {
 *         "success":   true/false ,
 *         "code":      成功代码/错误代码       ,
 *         "message":     业务描述/错误描述     ,
 *          .....
 *    } ,
 *    "data":  自定义具体返回数据格式
 * }
 * 
 */
public class QIResponse {

    private Meta meta = new Meta();
    private Object data = null;
    private ObjectMapper mapper = new ObjectMapper();

    public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString(){
		try {
			return mapper.writeValueAsString(this) ;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "json parse error" ;
	}
	public class Meta {
        private boolean success = true;
        private int code = 0;
        private String message = "default return message";

        public Meta(){
        	
        }

        public Meta(boolean success, int code,String message) {
            this.success = success;
            this.message = message;
            this.code = code ;
        }

		public boolean getSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public String toString(){
			try {
				return mapper.writeValueAsString(this) ;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return "json parse error" ;
		}

    }
	
	
	
	public static void main(String[] args){
		QIResponse qires = new QIResponse() ;
		qires.getMeta().setCode(0);
		qires.getMeta().setSuccess(true);
		qires.getMeta().setMessage("this api provide operations on user");
		List<Integer> ls = new ArrayList<Integer>() ;
		ls.add(1) ;
		ls.add(2);
		qires.setData(ls);
		System.out.println(qires.toString()); 
		
	}
}
