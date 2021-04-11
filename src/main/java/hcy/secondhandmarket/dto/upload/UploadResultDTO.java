package hcy.secondhandmarket.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable { // 직렬화를 할 수 있는 기본 조건을 가짐. (자바 객체 -> 바이트 형태)

    private String fileName;
    private String uuid;
    private String folderPath;

    // 전체 경로가 필요한 경우를 대비
    public String getImageURL() {
        try{
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }

}
