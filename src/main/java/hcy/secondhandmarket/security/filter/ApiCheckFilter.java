package hcy.secondhandmarket.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ApiCheckFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String pattern;

    public ApiCheckFilter(String pattern) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("REQUEST URI : " + request.getRequestURI());

        if(antPathMatcher.match(pattern, request.getRequestURI())) {
            log.info("ApiCheckFilter...................");
            log.info("ApiCheckFilter...................");
            log.info("ApiCheckFilter...................");

            return;
        }

        // 다음 필터로 넘어가는 역할.
        filterChain.doFilter(request, response);

    }
}
