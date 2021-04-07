package hcy.secondhandmarket.controller.api;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/PublicData/do")
public class PublicData extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PublicData() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        String serviceKey = "1EEC85B3-157C-39AA-8BD0-9D594116D4FE";
        String domain = "http://localhost:8080/PublicData/do";
        String addr = "http://api.vworld.kr/req/data?service=data&request=GetFeature&data=LT_C_ADSIDO_INFO&key="+ serviceKey +"&domain="+domain;

        String parameter = "";
//        serviceKey = URLEncoder.encode(serviceKey,"utf-8");
        PrintWriter out = response.getWriter();
        parameter = parameter + "&" + "request=GetFeature";
        parameter = parameter + "&" + "data=LT_C_ADSIDO_INFO";

        addr = addr + parameter;

        //addr = addr + serviceKey + parameter;
        URL url = new URL(addr);

        InputStream in = url.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(in, bos);
        in.close();
        bos.close();

        String mbos = bos.toString("UTF-8");

        byte[] b = mbos.getBytes("UTF-8");
        String s = new String(b, "UTF-8");
        out.println(s);

        JSONObject json = new JSONObject();
        json.put("data", s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
