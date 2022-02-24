package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }

        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining((name -> log.info("session name={}, value={}", name, session.getAttribute(name))));

        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval()); //세션 유효 시간(초)
        log.info("creationTime={}", new Date(session.getCreationTime())); //세션 생성일시
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime())); //세션과 연결된 사용자가 최근에 서버에 접근한 시간
                                                                            //클라이언트에서 서버로 sessionId(JSESSIONID)를 요청한 경우 갱신됨
        log.info("isNew={}", session.isNew()); //새로 생성된 세션인가?(true) 아니면 sessionId(JSESSIONID)를 요청해서 조회된 세션인가?

        return "세션 출력";
    }
}
