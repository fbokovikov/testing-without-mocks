package ru.yandex.testing.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * @author fbokovikov
 */
@Slf4j
@Component
public class RequestWrapperFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException
    {
        var wrappedRequest = new ContentCachingRequestWrapper(httpServletRequest);

        // pass through filter chain to do the actual request handling
        filterChain.doFilter(wrappedRequest, httpServletResponse);

        // only log request if there was an error
        if (httpServletResponse.getStatus() != HttpStatus.OK.value()) {
            // body can only be read after the actual request handling was done!
            byte[] buf = wrappedRequest.getContentAsByteArray();
            if (buf.length > 0) {
                var body = new String(buf, 0, buf.length, StandardCharsets.UTF_8);
                log.error(body);
            }
        }
    }
}
