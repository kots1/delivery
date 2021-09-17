package com.delivery.filter;

import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {


        private String encoding;

        public void destroy() {
        }

        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {



            String requestEncoding = request.getCharacterEncoding();
            if (requestEncoding == null) {
                request.setCharacterEncoding(encoding);
            }
            chain.doFilter(request, response);
        }

        public void init(FilterConfig fConfig) throws ServletException {
            encoding = fConfig.getInitParameter("encoding");
        }
}
