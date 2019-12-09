package zuminternet.pilot.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;

public class ResponseGenerator {
  static public HashMap withAuth () {
    HashMap send = new HashMap();
    Boolean success = true;
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userId = auth.getName();
      if (userId == "anonymousUser") {
        send.put("error", "Token Error");
        send.put("errorMessage", "로그인 후 이용해주세요");
        success = false;
      } else {
        send.put("userId", userId);
      }
    } catch (Exception e) {
      send.put("error", "Server Error");
      send.put("errorMessage", "알 수 없는 에러가 발생했습니다");
      success = false;
    }
    send.put("success", success);
    return send;
  }
}
