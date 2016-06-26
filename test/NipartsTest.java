import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class NipartsTest {
	
	// 不需要验证码登录的情况
	@Test
	public void testLogin() throws Exception{
		
		
		// 1. 先登录..
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String result;
		
		HttpPost post = new HttpPost("http://www.niparts.com/CheckLogin.ashx");
		
		List <NameValuePair> params = new ArrayList<NameValuePair>();
		
        params.add(new BasicNameValuePair("Name", "comedsh"));
        
        params.add(new BasicNameValuePair("Password", "comedsh006"));
        
        post.setEntity(new UrlEncodedFormEntity( params, HTTP.UTF_8) );  
        
        HttpResponse httpResponse = httpclient.execute(post);
        
        if(httpResponse.getStatusLine().getStatusCode() == 200)  
        {  
            
        	HttpEntity httpEntity = httpResponse.getEntity();  
            
            result = EntityUtils.toString(httpEntity); //取出应答字符串
            
            System.out.println( result );
            
        }else{
        	
        	System.out.println( " login faild ~ ");
        	
        }
        
        // HttpGet get = new HttpGet("http://www.niparts.com/CarCarList.aspx?mod=300544&code=FCA922D39D572923&name=VW");
        
        // 2. 访问登录以后才能访问的地址..
        HttpGet get = new HttpGet("http://www.niparts.com/index.aspx?action=search&supcomid=53312&artdesid=4992&cartype=6914176&code1=aa6e8d3495350e3edbd2bac4a3f0ecce&code2=4aa3995ea24a0e5678b2efcb285fa691&mfaid=10360");
                
        httpResponse = httpclient.execute(get);	//其中HttpGet是HttpUriRequst的子类
        
        if(httpResponse.getStatusLine().getStatusCode() == 200){
        	
            HttpEntity httpEntity = httpResponse.getEntity();
            
            result = EntityUtils.toString(httpEntity);//取出应答字符串
            
            // 一般来说都要删除多余的字符   
            result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            
            System.out.println( result );
            
        }else{ 
        
        	get.abort();
        	
        	System.err.println("login into the secret path failed ~ ");
        
        }
		
	}
	
}
