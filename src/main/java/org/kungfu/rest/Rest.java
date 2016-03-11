/**
 * 单纯、固执、容易体会到成就感；面对压力，能够挑灯夜战不眠不休；面对困难，能够迎难而上挑战自我。
 * 他们也会感到困惑与傍徨，但每个人心中都有一个比尔盖茨或是乔布斯的梦想“用智慧开创属于自己的事业”。
 * 其实我就是一个程序员。
 */
package org.kungfu.rest;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.jfinal.kit.StrKit;

import cn.dreampie.client.Client;
import cn.dreampie.client.ClientRequest;
import cn.dreampie.client.ClientResult;
import cn.dreampie.client.ClientUser;

/**
 * created by xiaofeixiang on 2016/1/21 10:48:18.
 */
public class Rest extends RestProvider {
	private static Logger LOG = Logger.getLogger(Rest.class);
	private String apiURI = null;
	private Client client = null;
	
	public Rest(String apiURI) {
		this.apiURI = apiURI;
		this.client = new Client(apiURI);
	}
	
	public Rest(String apiURI, String loginApi, ClientUser user) {
		this.apiURI = apiURI;
		this.client = new Client(apiURI, loginApi, user);
	}
	
	/**
	 * GET用来获取资源: GET（SELECT）：从服务器取出资源（一项或多项）。
	 * @param uri
	 * @return
	 */
	public String get(String uri) {
		LOG.info("REST API GET :  " + apiURI + uri);
		
		ClientResult getResult = client.build(addParam(new ClientRequest(uri), null)).get();
		
		 return getResult.getResult();
	}
	
	public String get(String uri, Map<String, String> params) {
		LOG.info("REST API GET :  " + apiURI + uri);
		
		ClientResult getResult = client.build(addParam(new ClientRequest(uri), params)).get();
		
		return getResult.getResult();
	}
	
	public String get(String uri, String jsonStr) {
		LOG.info("REST API GET :  " + apiURI + uri);
		
		ClientResult getResult = client.build(addJsonParam(new ClientRequest(uri), jsonStr)).get();
		
		return getResult.getResult();
	}
	
	/**
	 * POST（CREATE）：在服务器新建一个资源。
	 * @param uri
	 * @param params
	 * @return
	 */
	public ClientResult post(String uri, Map<String, String> params) {
		LOG.info("REST API POST :  " + apiURI + uri);
		
		ClientResult postResult = client.build(addParam(new ClientRequest(uri), params)).post();

		return postResult;
	}
	
	public ClientResult post(String uri, String jsonStr) {
		LOG.info("REST API POST :  " + apiURI + uri);
		
		ClientResult postResult = client.build(addJsonParam(new ClientRequest(uri), jsonStr)).post();

		return postResult;
	}
	
	/**
	 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
	 * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
	 * @param uri
	 * @param params
	 * @return
	 */
	public ClientResult put(String uri, Map<String, String> params) {
		LOG.info("REST API PUT :  " + apiURI + uri);
	
		ClientResult putResult = client.build(addParam(new ClientRequest(uri), params)).put();
		
		return putResult;
	}
	
	public ClientResult put(String uri, String jsonStr) {
		LOG.info("REST API PUT :  " + apiURI + uri);
	
		ClientResult putResult = client.build(addJsonParam(new ClientRequest(uri), jsonStr)).put();
		
		return putResult;
	}
	
	/**
	 * DELETE（DELETE）：从服务器删除资源。
	 * @param uri
	 * @return
	 */
	public ClientResult delete(String uri) {
		LOG.info("REST API DELETE :  " + apiURI + uri);
		
		 ClientResult deleteResult = client.build(addParam(new ClientRequest(uri), null)).delete();
		
		 return deleteResult;
	}
	
	public ClientResult delete(String uri, Map<String, String> params) {
		LOG.info("REST API DELETE :  " + apiURI + uri);
		
		 ClientResult deleteResult = client.build(addParam(new ClientRequest(uri), params)).delete();
		
		 return deleteResult;
	}
	
	public ClientResult delete(String uri, String jsonStr) {
		LOG.info("REST API DELETE :  " + apiURI + uri);
		
		ClientResult deleteResult = client.build(addJsonParam(new ClientRequest(uri), jsonStr)).delete();
		
		return deleteResult;
	}
	
	private ClientRequest addParam(ClientRequest request, Map<String, String> params) {
		if (StrKit.notNull(params)) {
			Iterator<Entry<String, String>> iter = params.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				request.addParam(entry.getKey(), entry.getValue());
			}
		}
		
		return request;
	}
	
	private ClientRequest addJsonParam(ClientRequest request, String jsonStr) {
		if (StrKit.notBlank(jsonStr)) { 
			request.setJsonParam(jsonStr);
		}
		return request;
	}
	
	// 还有两个不常用的HTTP动词:
	// HEAD：获取资源的元数据;
	// OPTIONS：获取信息，关于资源的哪些属性是客户端可以改变的。
}
