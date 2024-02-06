package ezen.bizqr.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import ezen.bizqr.user.security.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class OAuthService {

    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();

    public OAuthService(Environment env){
        this.env = env;
    }

    public void socialLogin(String code, String registrationId){
        String accessToken = getAccessToken(code, registrationId);
        JsonNode userResourceNode = getUserResource(accessToken, registrationId);
        System.out.println("accessToken = " + accessToken);

        UserVO uvo = new UserVO();
        log.info(">>> UserVO >>> ", uvo);
        switch (registrationId){
            case "google":{
                uvo.setEmail(userResourceNode.get("email").asText());
                uvo.setNickName(userResourceNode.get("name").asText());
                uvo.setPwd(userResourceNode.get("id").asText());
                break;
            }
            case "kakao":{
                //일단 보류
                break;
            }
            case "naver":{
                uvo.setEmail(userResourceNode.get("response").get("email").asText());
                uvo.setNickName(userResourceNode.get("response").get("nickname").asText());
                uvo.setPwd(userResourceNode.get("response").get("id").asText());
                break;
            }
            default:{
                throw new RuntimeException("UNSUPPORTED SOCIAL TYPE");
            }
        }
            log.info("email = " + uvo.getEmail());
            log.info("pwd = " + uvo.getPwd());
            log.info("nickName = " + uvo.getNickName());
    }

    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken, String registrationId){
        String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return  restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
