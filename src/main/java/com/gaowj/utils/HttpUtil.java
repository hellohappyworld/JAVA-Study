package com.gaowj.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String doPost(String url, Map<String, String> headers, Map params) {

        BufferedReader in = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost();

            request.setURI(new URI(url));
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> map : headers.entrySet()) {
                    request.addHeader(map.getKey(), map.getValue());
                }
            }
            List<NameValuePair> nvps = new ArrayList<>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            StringEntity entity = new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8);

            request.setEntity(entity);

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                return sb.toString();

            } else {
                System.out.println("状态码：" + code);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    //    public static String doGet(String url, Map<String, String> headers) {
    public static String doGet(String url) {

        String res = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(8000).build();
            HttpGet request = new HttpGet(url);
            request.setConfig(config);
//            if (headers != null && !headers.isEmpty()) {
//                for (Map.Entry<String, String> map : headers.entrySet()) {
//                    request.addHeader(map.getKey(), map.getValue());
//                }
//            }
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                res = EntityUtils.toString(response.getEntity(), "utf-8");
            }

        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }


    public static String doPost1(String url, String requestParams) {
        JSONObject jb = new JSONObject();
        jb.put("code", -1);
        String content = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(30 * 1000)
                    .setConnectTimeout(30 * 1000)
                    .build();
            HttpPost post = new HttpPost(url);
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(requestParams, "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                content = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            return content;
        } catch (SocketTimeoutException e) {
            logger.error("调用Dat+"
                    + ".aService接口超时,超时时间:" + 30
                    + "秒,url:" + url + ",参数：" + requestParams, e);
            return jb.toString();
        } catch (Exception e) {
            logger.error("调用DataService接口失败,url:" + url + ",参数：" + requestParams, e);
            return jb.toString();
        }
    }

    public static String doXmlPost(String url, String param) {
        String res = null;

        try {
            URL t_url = new URL(url);

            URLConnection con = t_url.openConnection();
            con.setDoOutput(true);
//            con.setRequestProperty("Pragma:", "no-cache");
//            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            String xmlInfo = param;
            ;
            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream()));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
//                System.out.println(line);
                res += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;

    }


    public static String doPost2(String url, String requestParams) {
        JSONObject jb = new JSONObject();
        jb.put("code", -1);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(30 * 1000)
                    .setConnectTimeout(30 * 1000)
                    .build();
            HttpPost post = new HttpPost(url);
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
//            post.setHeader("Content-Type","text/xml;charset=utf-8");
            StringEntity postingString = new StringEntity(requestParams, "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);
            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
            return content;
        } catch (SocketTimeoutException e) {
            logger.error("调用Dat+"
                    + ".aService接口超时,超时时间:" + 30
                    + "秒,url:" + url + ",参数：" + requestParams, e);
            return jb.toString();
        } catch (Exception e) {
            logger.error("调用DataService接口失败,url:" + url + ",参数：" + requestParams, e);
            return jb.toString();
        }
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("post推送结果：" + result);
        return result;
    }


    /**
     * 向指定URL发送GET方法的请求
     */
    public static String sendGet(String url, Map<String, String> header) throws IOException {
        String result = "";
        BufferedReader in = null;
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        //设置超时时间
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(15000);
        // 设置通用的请求属性
        if (header != null) {
            Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                System.out.println(entry.getKey() + ":" + entry.getValue());
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
//        Map<String, List<String>> map = connection.getHeaderFields();
        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        if (in != null) {
            in.close();
        }
        return result;
    }


    public static void main(String[] args) {
        String video = "1582948";
        String url = "http://local.shank.ifengidc.com/shankinterf/get/getWemediaListByPage/" + video + "-video/json?tarsset=slave";
        String s = HttpUtil.doGet(url);
        System.out.println(s);
    }

}
